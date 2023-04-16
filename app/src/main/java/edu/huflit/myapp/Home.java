package edu.huflit.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.huflit.myapp.Model.Photo;
import edu.huflit.myapp.Model.ThongTin;
import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.Model.Users;
import edu.huflit.myapp.adapter.NavigationAdapter;
import edu.huflit.myapp.adapter.PhotoAdater;
import edu.huflit.myapp.adapter.ThongTinAdapter;
import edu.huflit.myapp.adapter.TruyenTranhAdapter;

public class Home extends AppCompatActivity {
    GridView gridView;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> tranhArrayList;

    Toolbar toolbar;

    DrawerLayout drawerLayout;

    NavigationView navigationView;

    private ViewPager viewPager;
    private PhotoAdater photoAdater;

    private List<Photo> mListPhoto;

    private Timer mTimer;

    EditText edTSearch,edtName,edtEmail;

    String email,tentaikhoan;

    ListView listviewthongtin, listviewmanhinhchinh;

    ArrayList<ThongTin> navigationsArrayList;
    ArrayList<Users> usersArrayList;

    NavigationAdapter NavigationAdapter;
    ThongTinAdapter ThongTinAdapter;
    SharedPreferences sp;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Lấy dữ liệu từ trang Login qua
        Intent intent = getIntent();
        int i = intent.getIntExtra("phanquyen",0);
        int ID = intent.getIntExtra("Id",0);
        email = intent.getStringExtra("Email");
        tentaikhoan = intent.getStringExtra("TaiKhoan");

        AnhXa();

        ActionBar();
        //Phương lấy ảnh để set lên viewpager
        mListPhoto = GetListPhoto();
        photoAdater = new PhotoAdater(this,mListPhoto);
        viewPager.setAdapter(photoAdater);
        //Hàm animation
        autoImage();
        //Hàm hiện list truyện
        Init();
        SetUp();
        //Hàm tìm kiếm truyện
        SetClick();
        sp = getSharedPreferences("Data", MODE_PRIVATE);
        editor = sp.edit();

        listviewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Thông Tin Cá Nhân
                if(i == 0) {
                    Intent intent = new Intent(Home.this, Layout_User.class);
                    startActivity(intent);
                }
                //Đăng bài
                else if (i == 1) {
                    if(ID == 1) { //sửa id
                        Intent intent1 = new Intent(Home.this, TheLoai.class);
                        startActivity(intent1);
                    }
                    else{
                        Toast.makeText(Home.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                    }
                }
                //Yêu thích
                else if (i == 2) {
                    Intent intent = new Intent(Home.this, Home_Detail.class);
                    startActivity(intent);
                }
                //Thể Loại
                else if (i == 3) {
                    Intent intent = new Intent(Home.this, TheLoai.class);
                    startActivity(intent);
                }
                //Đổi Mật Khẩu
                else if (i == 4) {
                    Intent intent = new Intent(Home.this,ChangePass.class);
                    startActivity(intent);
                }
                //Setting
                else if (i == 5) {
                    Intent intent = new Intent(Home.this, Setting.class);
                    startActivity(intent);
                }
                //Đăng Xuất
                else if (i == 6) {
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(Home.this, MainLogin.class));
                    finish();
                }
            }
        });

    }

    //Thiết lập icon thông qua drawerLayout
    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.icon_drop);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    // View Pager
    private List<Photo> GetListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.viewpaper_dragonball));
        list.add(new Photo(R.drawable.viewpager_onepiece));
        list.add(new Photo(R.drawable.viewpager_doraemon));
        list.add(new Photo(R.drawable.viewpager_naruto));

        return list;
    }

    // Hiện các truyện
    private void Init() {
        tranhArrayList = new ArrayList<>();

        tranhArrayList.add(new TruyenTranh("Dragonball","Chapter 35","https://i.pinimg.com/564x/60/1b/90/601b90e64e522854e663d5a31d8b1ba0.jpg"));
        tranhArrayList.add(new TruyenTranh("Pokemon","Chapter 53","https://i.pinimg.com/750x/69/37/f3/6937f3e367d0f9c1202a6da0565b7c1b.jpg"));
        tranhArrayList.add(new TruyenTranh("Naruto","Chapter 45","https://i.pinimg.com/564x/18/c7/32/18c732f7a0c630014c239dfe4b8c1b42.jpg"));
        tranhArrayList.add(new TruyenTranh("Doraemon","Chapter 34","https://i.pinimg.com/564x/7f/ac/10/7fac103e4a43eda31d5896e48cabf28c.jpg"));
        tranhArrayList.add(new TruyenTranh("FairyTail","Chapter 35","https://i.truyenvua.com/ebook/190x247/fairy-tail-100-year-quest_1532514729.jpg?gt=hdfgdfg&mobile=2"));
        tranhArrayList.add(new TruyenTranh("Nguyên Tôn","Chapter 53","https://i.truyenvua.com/ebook/190x247/nguyen-ton_1513349962.jpg?gt=hdfgdfg&mobile=2"));
        tranhArrayList.add(new TruyenTranh("Học Viện Anh Hùng ","Chapter 45","https://i.truyenvua.com/ebook/190x247/boku-no-hero-academia_1552459650.jpg?gt=hdfgdfg&mobile=2"));
        tranhArrayList.add(new TruyenTranh("Estio","Chapter 34","https://i.truyenvua.com/ebook/190x247/estio_1667375485.jpg?gt=hdfgdfg&mobile=2"));
        tranhArrayList.add(new TruyenTranh("Quyết Chiến","Chapter 35","https://i.truyenvua.com/ebook/190x247/quyet-chien_1672882588.jpg?gt=hdfgdfg&mobile=2"));
        tranhArrayList.add(new TruyenTranh("Hỏa Long","Chapter 53","https://i.truyenvua.com/ebook/190x247/hoa-long-vainqueur_1666167689.jpg?gt=hdfgdfg&mobile=2"));
        tranhArrayList.add(new TruyenTranh("Chemy","Chapter 45","https://i.truyenvua.com/ebook/190x247/chemy_1660314535.jpg?gt=hdfgdfg&mobile=2"));
        tranhArrayList.add(new TruyenTranh("1331","Chapter 34","https://i.truyenvua.com/ebook/190x247/1331_1678606537.jpg?gt=hdfgdfg&mobile=2"));
        adapter = new TruyenTranhAdapter(this,0,tranhArrayList);

    }
    private void SetUp() {
        gridView.setAdapter(adapter);
    }


    //Phương thực gọi các biến
    private void AnhXa() {
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edTSearch = findViewById(R.id.edtSearch);
        gridView = findViewById(R.id.gVDSTRUYEN);
        viewPager = findViewById(R.id.viewFlipper);
        toolbar = findViewById(R.id.Toolbar);
        navigationView = findViewById(R.id.navigationView);
        listviewmanhinhchinh = findViewById(R.id.manhinhchuyenmuc);
        listviewthongtin = findViewById(R.id.manthongtin);
        drawerLayout = findViewById(R.id.drawerlayout);
        //Thông tin
        usersArrayList = new ArrayList<>();
        usersArrayList.add(new Users(tentaikhoan,email));
        ThongTinAdapter = new ThongTinAdapter(this, R.layout.navigation_thongtin, usersArrayList);
        listviewthongtin.setAdapter(ThongTinAdapter);



        //Chuyên mục
        navigationsArrayList = new ArrayList<>();
        navigationsArrayList.add(new ThongTin("Thông Tin", R.drawable.icon_login));
        navigationsArrayList.add(new ThongTin("Đăng bài",R.drawable.icon_dangbai));
        navigationsArrayList.add(new ThongTin("Ưa thích",R.drawable.baseline_favorite_red));
        navigationsArrayList.add(new ThongTin("Thể Loại",R.drawable.icon_theloai));
        navigationsArrayList.add(new ThongTin("Đổi mật khẩu",R.drawable.icon_doimatkhau));
        navigationsArrayList.add(new ThongTin("Setting",R.drawable.icon_setting));
        navigationsArrayList.add(new ThongTin("Đăng Xuất", R.drawable.icon_logout));

        NavigationAdapter = new NavigationAdapter(this,R.layout.layout_chuyenmuc,navigationsArrayList);
        listviewmanhinhchinh.setAdapter(NavigationAdapter);
    }

    // Thanh tìm kiếm truyện
    private void SetClick() {
        edTSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edTSearch.getText().toString();
                adapter.SearchTruyen(s);
            }
        });
    }

//    public void Onclick() {
//        gridView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Home.this,Home_Detail.class);
//                startActivity(i);
//            }
//        });
//    }


    // Hình ảnh tự chuyển động
    private void autoImage(){
        if(mListPhoto == null || mListPhoto.isEmpty() || viewPager == null) {
            return;
        }
        if(mTimer == null){
            mTimer = new Timer();
        }

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size()-1;
                        if(currentItem<totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }
                        else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
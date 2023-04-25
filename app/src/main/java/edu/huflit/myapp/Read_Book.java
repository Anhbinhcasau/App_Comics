package edu.huflit.myapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.myapp.Model.List_Chapter;
import edu.huflit.myapp.Model.TapTruyen;
import edu.huflit.myapp.adapter.Chapter_Adapter;

import edu.huflit.myapp.adapter.Image_Adapter;

import edu.huflit.myapp.database.dtbApp;


public class Read_Book extends AppCompatActivity {

    private RelativeLayout rlTopBar, rlBottomBar;

    private boolean hidden = true;

    ListView lvComic, mlvChapter;
    Button btnShowChapter, btnExit, btnThemTap;
    dtbApp dbApp;
    ArrayList<TapTruyen> tapTruyenArrayList;
    String tenUser, tenTruyen, tap;

    Chapter_Adapter adapterChapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        tenTruyen = getIntent().getStringExtra("TenTruyen");
        tenUser = getIntent().getStringExtra("TenUser");
        tap = Integer.toString(getIntent().getIntExtra("Tap", 0));
        Toast.makeText(this, "Bạn đang xem truyên "+ tenTruyen+" Tập "+tap, Toast.LENGTH_SHORT).show();



        FirebaseStorage storage = FirebaseStorage.getInstance("gs://truyen-9f7f6.appspot.com/");
        StorageReference storageRef = storage.getReference().child(tenTruyen);
        StorageReference imageRef = storageRef.child("Tập "+tap);

        imageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                List<StorageReference> imageRefs = listResult.getItems();
                List<Task<byte[]>> tasks = new ArrayList<>();
                for (StorageReference imageRef : imageRefs) {
                    tasks.add(imageRef.getBytes(Long.MAX_VALUE));
                }
                Tasks.whenAllSuccess(tasks).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
                    @Override
                    public void onSuccess(List<Object> objects) {
                        List<Bitmap> bitmaps = new ArrayList<>();
                        for (Object object : objects) {
                            byte[] bytes = (byte[]) object;
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            bitmaps.add(bitmap);
                        }
                        Image_Adapter adapterHinh = new Image_Adapter(Read_Book.this, bitmaps);
                        lvComic.setAdapter(adapterHinh);
                    }
                });
            }
        });

        btnShowChapter = (Button) findViewById(R.id.btnShowChapter);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnThemTap = findViewById(R.id.btnThemTap);
        btnShowChapter = (Button) findViewById(R.id.btnShowChapter);
        btnExit = (Button) findViewById(R.id.btnExit);
        lvComic = findViewById(R.id.lvComic);
        rlTopBar = findViewById(R.id.rlTopBar);
        rlBottomBar= findViewById(R.id.rlBottomBar);
        mlvChapter = (ListView) findViewById(R.id.lvChapter);

        dbApp = new dtbApp(this);


        ArrayList arrChapter = new ArrayList<List_Chapter>();

        Init();


        btnShowChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawerChapter = findViewById(R.id.drawer_chapter);
                drawerChapter.openDrawer((GravityCompat.END));
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvComic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private static final long DOUBLE_CLICK_TIME_DELTA = 300; //Thời gian giữa 2 lần click (0.3s)
            private long lastClickTime = 0; // Khởi tạo giá trị bằng 0
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // gán thời gian hiện tại
                long clickTime = System.currentTimeMillis();
                if(clickTime - lastClickTime <= DOUBLE_CLICK_TIME_DELTA){
                    if(hidden){
                        rlBottomBar.setVisibility(View.INVISIBLE);
                        rlTopBar.setVisibility(View.INVISIBLE);
                        hidden = false;
                    }else{
                        rlBottomBar.setVisibility(View.VISIBLE);
                        rlTopBar.setVisibility(View.VISIBLE);
                        hidden = true;
                    }
                }
                lastClickTime = clickTime;
            }
        });
        btnThemTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Read_Book.this, ThemTap.class);
                startActivity(i);
            }
        });
    }
    //hiện các tập
    private void Init() {
        Cursor cursor = dbApp.getDataTap();
        tapTruyenArrayList = new ArrayList<TapTruyen>();

        while (cursor.moveToNext()){
            TapTruyen tapTruyen = new TapTruyen();
            int id = cursor.getInt(0);
            String Ten = cursor.getString(1);
            tapTruyen.setId(id);
            tapTruyen.setTenTap(Ten);

            tapTruyenArrayList.add(tapTruyen);
        }
        cursor.moveToFirst();
        //Thực hiện khi không sử dụng
        cursor.close();
        adapterChapter = new Chapter_Adapter(this,0, tapTruyenArrayList);
        mlvChapter.setAdapter(adapterChapter);
    }

}
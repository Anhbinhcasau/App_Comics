package edu.huflit.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.huflit.myapp.database.dtbApp;

public class MainLogin extends AppCompatActivity {
    EditText edtTK,edtMK;
    Button btnDangNhap, btnDangKy;
    dtbApp dtbApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        dtbApp = new dtbApp(this);


        //Tạo sự kiện click button với Intent
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainLogin.this,MainDangKy.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo 2 biến Tài khoản và Mật Khẩu
                String mtaikhoan = edtTK.getText().toString();
                String mmatkhau = edtMK.getText().toString();

                //Sử dụng con trỏ để lấy dữ liệu từ database
                Cursor cursor = dtbApp.getData();
                //Thực hiền vòng lặp để lấy dữ liệu từ cursor với moveToNext di chuyển tiếp
                while (cursor.moveToNext()) {
                    //Lấy dữ liệu và gán vào biến
                    // ô 0 : Id - ô 1 : Tài Khoản - ô 2 : Mật Khẩu - ô 3 : Email - ô 4 : Phân Quyền

                    String datataikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);
                    if(datataikhoan.equals(mtaikhoan) && datamatkhau.equals(mmatkhau)) {

                        //Lấy dữ liệu phân quyền và id
                        int id = cursor.getInt(0);
                        int phanquyen =cursor.getInt(4);
                        String email =cursor.getString(3);
                        String tk = cursor.getString(1);

                        //Chuyển vào màn hình HOME app đọc truyện
                        Intent i = new Intent(MainLogin.this,Home.class);

                        Toast.makeText(MainLogin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        //Gửi dữ liệu qua Activity là Main Home
                        i.putExtra("phanquyen",phanquyen);
                        i.putExtra("Id",id);
                        i.putExtra("TaiKhoan",tk);
                        i.putExtra("Email",email);

                        startActivity(i);

                    }
                }
                //Trả cursor về đầu
                cursor.moveToFirst();
                //Thực hiện khi không sử dụng
                cursor.close();
            }
        });
    }


    public void AnhXa() {
        edtTK = findViewById(R.id.taikhoan);
        edtMK = findViewById(R.id.matkhau);
        btnDangNhap = findViewById(R.id.dangnhap);
        btnDangKy = findViewById(R.id.dangky);
    }
}
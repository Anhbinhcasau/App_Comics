package edu.huflit.myapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.Model.Users;
import edu.huflit.myapp.database.dtbApp;

public class ThemTruyen extends AppCompatActivity {

    EditText edtTieuDe, edtNoiDung, edtIMG, edtTacGia, edtTenTap;
    Button btnThem;
    dtbApp dbApp;
    private static int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_truyen);

        edtNoiDung = findViewById(R.id.edtNoiDungTruyen);
        edtTieuDe = findViewById(R.id.edtTieuDe);
        edtIMG = findViewById(R.id.edtIMG);
        btnThem = findViewById(R.id.btnThem);
        edtTacGia = findViewById(R.id.edtTacGia);

        dbApp = new dtbApp(this);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tieuDe = edtTieuDe.getText().toString();
                String noiDung = edtNoiDung.getText().toString();
                String img = edtIMG.getText().toString();
                String tacGia = edtTacGia.getText().toString();

                TruyenTranh truyenTranh = CreatTruyen();

                if(tieuDe.equals("") || noiDung.equals("") || img.equals("") || tacGia.equals(""))
                {
                    Toast.makeText(ThemTruyen.this, "Đừng bỏ trống nhé@@", Toast.LENGTH_SHORT).show();
                    Log.e("ERR","Hãy nhập đầy đủ thong tin");
                }
                else {
                    dbApp.Addtruyen(truyenTranh);
                    Toast.makeText(ThemTruyen.this, "Thêm truyện thành công!!", Toast.LENGTH_SHORT).show();
                    //Intent i = new Intent(ThemTruyen.this,ThemTruyen.class);
                    finish();
                    //startActivity(i);
                }
            }
        });

    }
    private TruyenTranh CreatTruyen(){
        String tieuDe = edtTieuDe.getText().toString();
        String noiDung = edtNoiDung.getText().toString();
        String img = edtIMG.getText().toString();
        String tacGia = edtTacGia.getText().toString();

        TruyenTranh truyenTranh = new TruyenTranh();

        truyenTranh.setIdTruyen(id);
        truyenTranh.setTenTruyen(tieuDe);
        truyenTranh.setLinkAnh(img);
        truyenTranh.setNoiDungTruyen(noiDung);
        truyenTranh.setTacGia(tacGia);

        return truyenTranh;
    }

}
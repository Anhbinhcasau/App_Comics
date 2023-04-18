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

import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.Model.Users;
import edu.huflit.myapp.database.dtbApp;

public class ThemTruyen extends AppCompatActivity {

    EditText edtIDTruyen, edtTieuDe, edtNoiDung, edtIMG, edtTacGia;
    Button btnThem;
    ImageView imgTruyen;

    dtbApp dbApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_truyen);

        edtIDTruyen = findViewById(R.id.edtIDTruyen);
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
                    Intent i = new Intent(getApplicationContext(),Home.class);
                    startActivity(i);
                }
            }
        });
    }
    private TruyenTranh CreatTruyen(){
        String tieuDe = edtTieuDe.getText().toString();
        String noiDung = edtNoiDung.getText().toString();
        String img = edtIMG.getText().toString();
        String tacGia = edtTacGia.getText().toString();

        Intent intent = getIntent();
        int id = intent.getIntExtra("Id",0);

        TruyenTranh truyenTranh = new TruyenTranh(tieuDe,noiDung, img, tacGia, id);
        return truyenTranh;
    }

    @Override
    protected void onActivityResult(int request, int result, @Nullable Intent data)
    {
        super.onActivityResult(request, result, data);
        if (result == RESULT_OK && request == 1) {
            Uri uri = data.getData();
            imgTruyen.setImageURI(uri);
        }
    }
}
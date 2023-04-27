package edu.huflit.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.database.dtbApp;

public class update_truyen extends AppCompatActivity {

    EditText edtTieuDe,edtNoiDung,edtTacGia,edtAnh;
    Button btnUpdate;

    dtbApp dtbApp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_truyen);

        edtTieuDe=findViewById(R.id.edtSuaTieuDe);
        edtNoiDung=findViewById(R.id.edtSuaNoiDung);
        edtTacGia=findViewById(R.id.edtSuaTacGia);
        edtAnh=findViewById(R.id.edtSuaIMG);
        btnUpdate=findViewById(R.id.btnUpdate);

        dtbApp=new dtbApp(this);

        String tieude= getIntent().getStringExtra("Ten");
        edtTieuDe.setText(tieude);
        String noidung = getIntent().getStringExtra("tomtat");
        edtNoiDung.setText(noidung);
        String tacgia= getIntent().getStringExtra("tacgia");
        edtTacGia.setText(tacgia);
        String anh = getIntent().getStringExtra("anh");
        edtAnh.setText(anh);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TruyenTranh truyenTranh = CreatTruyen();
                dtbApp.Edit(truyenTranh);
//                Intent i = new Intent(update_truyen.this,LayoutAdmin.class);
//                startActivity(i);
                finish();
                Toast.makeText(update_truyen.this, "Update truyện thành công!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private TruyenTranh CreatTruyen() {
        String tieuDe = edtTieuDe.getText().toString();
        String noiDung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();
        String tacGia = edtTacGia.getText().toString();

        Intent intent = getIntent();
        int id = intent.getIntExtra("Id", 0);
        TruyenTranh truyenTranh = new TruyenTranh(tieuDe,noiDung,img,tacGia,id);
        return truyenTranh;
    }
}
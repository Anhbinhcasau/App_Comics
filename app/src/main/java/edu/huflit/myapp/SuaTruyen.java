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

public class SuaTruyen extends AppCompatActivity {

    EditText edtTieuDe, edtNoiDung, edtTacGia, edtAnh;
    dtbApp dtbapp;
    Button btnUpdate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_truyen);

        edtTieuDe = findViewById(R.id.edtTieuDe);
        edtNoiDung = findViewById(R.id.edtNoiDungTruyen);
        edtTacGia = findViewById(R.id.edtTacGia);
        edtAnh = findViewById(R.id.edtIMG);
        btnUpdate = findViewById(R.id.btnUpdate);

        dtbapp=new dtbApp(this);

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
                dtbapp.Edit(truyenTranh);
//                Intent i = new Intent(update_truyen.this,LayoutAdmin.class);
//                startActivity(i);
                finish();
                Toast.makeText(SuaTruyen.this, "Update truyện thành công!!", Toast.LENGTH_SHORT).show();
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
        TruyenTranh truyenTranh = new TruyenTranh();
        truyenTranh.setIdTruyen(id);
        truyenTranh.setTenTruyen(tieuDe);
        truyenTranh.setNoiDungTruyen(noiDung);
        truyenTranh.setLinkAnh(img);
        truyenTranh.setTacGia(tacGia);
        return truyenTranh;
    }
}
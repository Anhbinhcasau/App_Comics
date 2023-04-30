package edu.huflit.myapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.Model.YeuThich;
import edu.huflit.myapp.adapter.Like_Adapter;
import edu.huflit.myapp.database.dtbApp;

public class LayoutLike extends AppCompatActivity {

    ListView listView1;
    public static dtbApp dtbapp;
    ImageView imgTim;
    public static ArrayList<TruyenTranh> tranhArrayListYT;
    Like_Adapter adapter;
    int id, IDtruyen;
    String anhTruyen, tenTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_like);

        dtbapp = new dtbApp(this);

        id = getIntent().getIntExtra("TaiKhoan", 0);
        anhTruyen = getIntent().getStringExtra("anh");
        tenTruyen = getIntent().getStringExtra("Ten");
        IDtruyen = getIntent().getIntExtra("Id", IDtruyen);

        listView1 = findViewById(R.id.lvyeuthich);


        Init();
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = dtbapp.getDataTruyen();
                if (cursor.moveToPosition(i)) {
                    // Di chuyển con trỏ đến vị trí item được chọn
                    int idTruyen = cursor.getInt(0);
                    String Ten = cursor.getString(1);
                    String tomtat = cursor.getString(2);
                    String anh = cursor.getString(3);
                    String tacgia = cursor.getString(4);
                    Intent a = new Intent(LayoutLike.this, Home_Detail.class);
                    a.putExtra("anh", anh);
                    a.putExtra("Id", idTruyen);
                    a.putExtra("Ten", Ten);
                    a.putExtra("tomtat", tomtat);
                    a.putExtra("tacgia", tacgia);
                    startActivity(a);
                }
                cursor.close();
            }
        });
    }

    private void Init(){
        //Hiện các truyện yêu thích
        Cursor likee = dtbapp.getDataYeuThich();
        Cursor cursor = dtbapp.getDataTruyen();
        tranhArrayListYT = new ArrayList<TruyenTranh>();
        while (likee.moveToNext() && cursor.moveToNext()){
            if (likee.getInt(1) == 1 && likee.getInt(3) == id) {
                TruyenTranh truyenTranh= new TruyenTranh();
                int id = cursor.getInt(0);
                String Ten =cursor.getString(1);
                String anh = cursor.getString(3);
                truyenTranh.setIdTruyen(id);
                truyenTranh.setTenTruyen(Ten);
                truyenTranh.setLinkAnh(anh);
                tranhArrayListYT.add(truyenTranh);
            }
        }
        likee.moveToFirst();
        //Thực hiện khi không sử dụng
        likee.close();
        cursor.close();
        adapter = new Like_Adapter(this, 0, tranhArrayListYT);
        listView1.setAdapter(adapter);
    }
    public YeuThich DeleteYT() {
        YeuThich yeuThich = new YeuThich();
        yeuThich.setIdTruyen(IDtruyen);
        yeuThich.setIdTK(id);
        yeuThich.setTrangThai(0);
        return yeuThich;
    }
}
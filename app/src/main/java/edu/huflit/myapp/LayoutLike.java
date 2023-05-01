package edu.huflit.myapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static ArrayList<TruyenTranh> tranhArrayListYT;
    Like_Adapter adapter;
    int id, IDtruyen;
    String anhTruyen, tenTruyen;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_like);

        dtbapp = new dtbApp(this);

        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        IDtruyen = sharedPref.getInt("idTruyen", 0);
        id = sharedPref.getInt("Id", 0);
//        id = getIntent().getIntExtra("Id", 0);
//        IDtruyen = getIntent().getIntExtra("idTruyen", 0);
        anhTruyen = getIntent().getStringExtra("anh");
        tenTruyen = getIntent().getStringExtra("Ten");


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
                    String theloai = cursor.getString(5);
                    Intent a = new Intent(LayoutLike.this, Home_Detail.class);
                    a.putExtra("anh", anh);
                    a.putExtra("idTruyen", idTruyen);
                    a.putExtra("Ten", Ten);
                    a.putExtra("tomtat", tomtat);
                    a.putExtra("tacgia", tacgia);
                    a.putExtra("TL", theloai);
                    startActivity(a);
                }
                cursor.close();
            }
        });
    }

    private void Init(){
        //Hiện các truyện yêu thích
        Cursor cursor1 = dtbapp.getDataYeuThich();
        Cursor cursor = dtbapp.getDataTruyen();
        tranhArrayListYT = new ArrayList<TruyenTranh>();
        while (cursor.moveToNext() && cursor1.moveToNext()){
            if (cursor1.getInt(1) == 1 ){
                TruyenTranh truyenTranh = new TruyenTranh();
                truyenTranh.setIdTruyen(cursor.getInt(0));
                truyenTranh.setTenTruyen(cursor.getString(1));
                truyenTranh.setNoiDungTruyen(cursor.getString(2));
                truyenTranh.setLinkAnh(cursor.getString(3));
                truyenTranh.setTacGia(cursor.getString(4));
                truyenTranh.setCate(cursor.getString(6));
                tranhArrayListYT.add(truyenTranh);
            }
        }
        //Thực hiện khi không sử dụng
        cursor.close();
        cursor1.close();
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
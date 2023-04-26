package edu.huflit.myapp;

import static android.content.ContentValues.TAG;

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
import edu.huflit.myapp.adapter.AdminTruyenAdapter;
import edu.huflit.myapp.adapter.Like_Adapter;
import edu.huflit.myapp.database.dtbApp;

public class LayoutLike extends AppCompatActivity {

    ListView listView1;
    dtbApp dtbapp;
    public ArrayList<TruyenTranh> tranhArrayListYT;
    Like_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_like);

        dtbapp = new dtbApp(this);

        listView1 = findViewById(R.id.lvyeuthich);

        //Hiện các truyện yêu thích
        Cursor cursor = dtbapp.getDataTruyen();
        tranhArrayListYT = new ArrayList<TruyenTranh>();

        while (cursor.moveToNext()){
            if (cursor.getInt(6) == 1){
                TruyenTranh truyenTranh= new TruyenTranh();
                int id = cursor.getInt(0);
                String Ten =cursor.getString(1);
                String anh = cursor.getString(4);
                truyenTranh.setIdTruyen(id);
                truyenTranh.setTenTruyen(Ten);
                truyenTranh.setLinkAnh(anh);
                tranhArrayListYT.add(truyenTranh);
            }
        }
        cursor.moveToFirst();
        //Thực hiện khi không sử dụng
        cursor.close();
        adapter = new Like_Adapter(this, 0, tranhArrayListYT);
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = dtbapp.getDataTruyen();
                if (cursor.moveToPosition(i)) {
                    // Di chuyển con trỏ đến vị trí item được chọn
                    int idTruyen = cursor.getInt(0);
                    String Ten = cursor.getString(1);
                    String tomtat = cursor.getString(2);
                    String theLoai = cursor.getString(3);
                    String anh = cursor.getString(4);
                    String tacgia = cursor.getString(5);
                    Intent a = new Intent(LayoutLike.this, Home_Detail.class);
                    a.putExtra("anh", anh);
                    a.putExtra("theLoai", theLoai);
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
}
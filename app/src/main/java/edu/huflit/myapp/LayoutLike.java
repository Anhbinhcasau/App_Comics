package edu.huflit.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.adapter.AdminTruyenAdapter;
import edu.huflit.myapp.database.dtbApp;

public class LayoutLike extends AppCompatActivity {

    ListView listView;
    dtbApp dtbapp;
    ArrayList<TruyenTranh> tranhArrayList;
    AdminTruyenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_like);

        listView = findViewById(R.id.lvyeuthich);
        Uppppp();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    //Hiện các truyện yêu thích
    private void Uppppp() {
        Cursor cursor = dtbapp.getDataTruyen();
        tranhArrayList = new ArrayList<TruyenTranh>();

        while (cursor.moveToNext()){
            TruyenTranh truyenTranh= new TruyenTranh();
            int id = cursor.getInt(0);
            String Ten =cursor.getString(1);
            String anh = cursor.getString(4);
            int likeee = cursor.getInt(6);
            if (likeee == 1){
                truyenTranh.setIdTruyen(id);
                truyenTranh.setTenTruyen(Ten);
                truyenTranh.setLinkAnh(anh);
                truyenTranh.setYeuThich(likeee);
                tranhArrayList.add(truyenTranh);
            }
        }
        cursor.moveToFirst();
        //Thực hiện khi không sử dụng
        cursor.close();
        adapter = new AdminTruyenAdapter(this,0,tranhArrayList);
        listView.setAdapter(adapter);

    }
}
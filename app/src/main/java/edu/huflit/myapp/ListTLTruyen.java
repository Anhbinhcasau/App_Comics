package edu.huflit.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.adapter.AdminTruyenAdapter;
import edu.huflit.myapp.adapter.Like_Adapter;
import edu.huflit.myapp.database.dtbApp;

public class ListTLTruyen extends AppCompatActivity {

    dtbApp dtbapp;
    ArrayList<TruyenTranh> tranhArrayList;
    String cate;
    AdminTruyenAdapter adapter;
    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tltruyen);
        dtbapp = new dtbApp(this);

        listView = findViewById(R.id.lv);
        Init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
    private void Init(){
        //Hiện các truyện theo thể loại
        tranhArrayList = new ArrayList<TruyenTranh>();
        cate = getIntent().getStringExtra("TL");
        Cursor csTruyen = dtbapp.getDataTruyenByCate(cate);
        if (csTruyen.moveToFirst()) {
            TruyenTranh truyenTranh = new TruyenTranh();
            truyenTranh.setLinkAnh(csTruyen.getString(2));
            truyenTranh.setTenTruyen(csTruyen.getString(1));
            tranhArrayList.add(truyenTranh);
        }
        csTruyen.close();
        adapter = new AdminTruyenAdapter(this, 0, tranhArrayList);
        listView.setAdapter(adapter);
    }
}
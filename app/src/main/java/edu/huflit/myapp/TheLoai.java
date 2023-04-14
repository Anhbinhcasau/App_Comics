package edu.huflit.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.huflit.myapp.Model.TheLoaiTruyen;
import edu.huflit.myapp.adapter.TheLoai_Adapter;

public class TheLoai extends AppCompatActivity {

    ListView listView;
    ArrayList<TheLoaiTruyen> theLoaiArrayList;
    TheLoai_Adapter theLoai_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        listView = findViewById(R.id.list_theloai);
        AnhXa();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    Toast.makeText(TheLoai.this, "Thể loại phiêu lưu", Toast.LENGTH_SHORT).show();
                }
                else if (i == 1) {
                    Toast.makeText(TheLoai.this, "Thể loại cổ tích", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void AnhXa(){
        //Thể loại
        theLoaiArrayList = new ArrayList<>();
        theLoaiArrayList.add(new TheLoaiTruyen("Phiêu lưu" ,R.drawable.icon_adven));
        theLoaiArrayList.add(new TheLoaiTruyen("Cổ tích" ,R.drawable.icon_adven));
        theLoaiArrayList.add(new TheLoaiTruyen("Viễn tưởng" ,R.drawable.icon_adven));
        theLoai_adapter = new TheLoai_Adapter(this,R.layout.layout_theloai,theLoaiArrayList);
        listView.setAdapter(theLoai_adapter);

    }
}
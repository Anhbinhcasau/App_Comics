package edu.huflit.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;

import edu.huflit.myapp.Model.List_Chapter;
import edu.huflit.myapp.adapter.Chapter_Adapter;

public class Read_Book extends AppCompatActivity {

    private RelativeLayout rlTopBar, rlBottomBar;

    private boolean hidden = true;

    TextView tvNovel;
    ListView lvComic, mlvChapter;
    Button btnShowChapter, btnExit;
    String items[] = new String[] {"chap1","chap2","chap3","chap1","chap2","chap3","chap2","chap3","chap1","chap2","chap3","chap2","chap3","chap1","chap2","chap3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        btnShowChapter = (Button) findViewById(R.id.btnShowChapter);
        btnExit = (Button) findViewById(R.id.btnExit);


        tvNovel = findViewById(R.id.tvNovel);
        rlTopBar = findViewById(R.id.rlTopBar);
        rlBottomBar= findViewById(R.id.rlBottomBar);

        mlvChapter = (ListView) findViewById(R.id.lvChapter);


        ArrayList arrChapter = new ArrayList<List_Chapter>();

        for (int i = 0; i < items.length; i++) {
            List_Chapter chapter = new List_Chapter(items[i]);
            arrChapter.add(chapter);
        }
        ArrayAdapter adapterChapter = new Chapter_Adapter(this, R.layout.item_custom_list_view_chapter, arrChapter);
        mlvChapter.setAdapter(adapterChapter);

        btnShowChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawerChapter = findViewById(R.id.drawer_chapter);
                drawerChapter.openDrawer((GravityCompat.END));
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
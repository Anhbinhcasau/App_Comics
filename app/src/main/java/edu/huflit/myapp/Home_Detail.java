package edu.huflit.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.myapp.Model.Dialog_rating;
import edu.huflit.myapp.Model.TapTruyen;
import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.database.dtbApp;

public class Home_Detail extends AppCompatActivity {
    Button mBtnSummary,  mBtnChapter, mBtnComment , mBtnContinue ;
    ImageButton mBntExt;
    TextView mTvSummary, tvNameComic, tvSummary,tvNameAuthor;
    ImageView mImgFavorite, mImgRating, imgMain;
    ListView mlvChapter ;
    dtbApp dtbapp;
    String tacgia, tomTat, tenTruyen, anhTruyen, tenUser;
    int IDtruyen, pq;


    boolean hidden = true;
    boolean isColor = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        AnhXa();



        IDtruyen = getIntent().getIntExtra("Id",0);
        anhTruyen = getIntent().getStringExtra("anh");
        tenTruyen = getIntent().getStringExtra("Ten");
        tomTat  = getIntent().getStringExtra("tomtat");
        tacgia = getIntent().getStringExtra("tacgia");
        tenUser = getIntent().getStringExtra("TenUser");
        pq = getIntent().getIntExtra("phanquyen", 0);


        ShowTap();
        ClickTap();

        tvNameComic.setText(tenTruyen);
        tvSummary.setText(tomTat);
        tvNameAuthor.setText(tacgia);
        Glide.with(this).load(anhTruyen).fitCenter().into(imgMain);

        mBtnChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvSummary.setVisibility(View.INVISIBLE);
                mBtnComment.setVisibility(View.INVISIBLE);
                mlvChapter.setVisibility(View.VISIBLE);
                if (hidden) {
                    mBtnChapter.setBackgroundResource(R.color.teal_200);
                    mBtnSummary.setBackgroundColor(Color.WHITE);
                    hidden = true;
                }
            }
        });

        mBtnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvSummary.setVisibility(View.VISIBLE);
                mBtnComment.setVisibility(View.VISIBLE);
                mlvChapter.setVisibility(View.INVISIBLE);

                if (hidden) {
                    mBtnSummary.setBackgroundResource(R.drawable.primary_drawable);
                    mBtnChapter.setBackgroundColor(Color.WHITE);
                    hidden = true;
                }
            }
        });
        mImgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isColor){
                    mImgFavorite.setBackgroundResource(R.drawable.baseline_favorite_red);
                    isColor = false;
                }else {
                    mImgFavorite.setBackgroundResource(R.drawable.baseline_favorite_shadow);
                    isColor = true;
                }
            }
        });
        mBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dtbapp.getDataTapByIDTruyen(IDtruyen);
                if (pq == 2 && cursor == null && cursor.moveToFirst()){
                    Toast.makeText(Home_Detail.this, "Truyện chưa có tập nào xin chờ Admin thêm lại sau", Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(Home_Detail.this, Read_Book.class );
                    i.putExtra("TenTruyen", tenTruyen);
                    i.putExtra("TenUser", tenUser);
                    i.putExtra("Tap", 1);
                    i.putExtra("phanquyen", pq);
                    i.putExtra("idTruyen", IDtruyen);
                    startActivity(i);
                }

            }
        });
        mImgRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog_rating rating = new Dialog_rating(Home_Detail.this);
                rating.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(Home_Detail.this, android.R.color.transparent)));
                rating.setCancelable(false);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(rating.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                rating.show();
                rating.getWindow().setAttributes(lp);
            }
        });
        mBtnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home_Detail.this, Comment.class );
                i.putExtra("TenUser", tenUser);
                i.putExtra("idTruyen", IDtruyen);
                startActivity(i);
            }
        });
        mBntExt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void AnhXa(){
        mBtnSummary = (Button) findViewById(R.id.btnSummary);
        mBtnChapter = (Button) findViewById(R.id.btnChapter);
        mBtnComment = (Button) findViewById(R.id.btnComment);
        mImgFavorite = (ImageView) findViewById(R.id.imgFavorite);
        mImgRating = (ImageView) findViewById(R.id.imgRating);
        mBtnContinue = (Button) findViewById(R.id.btnStart);
        mBntExt = (ImageButton) findViewById(R.id.btnExt);
        mTvSummary = (TextView) findViewById(R.id.tvSummary);
        mlvChapter = (ListView) findViewById(R.id.lvchap);
        imgMain = (ImageView) findViewById(R.id.imgMain);
        tvNameComic = findViewById(R.id.tvNameComic);
        tvNameAuthor = findViewById(R.id.tvNameAuthor);
        tvSummary = findViewById(R.id.tvSummary);
        dtbapp = new dtbApp(this);
    }
    public void ClickTap(){
        mlvChapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = dtbapp.getDataTapByIDTruyen(IDtruyen);
                if (cursor.moveToPosition(position)){
                    int tenTap = Integer.parseInt(cursor.getString(1));
                    Intent i = new Intent(Home_Detail.this, Read_Book.class);
                    i.putExtra("Tap", tenTap);
                    i.putExtra("TenTruyen", tenTruyen);
                    i.putExtra("TenUser", tenUser);
                    i.putExtra("phanquyen", pq);
                    i.putExtra("idTruyen", IDtruyen);
                    startActivity(i);
                }
                cursor.close();
            }
        });
    }

    public void  ShowTap(){
        Cursor cursor = dtbapp.getDataTap();
        List<String> items = new ArrayList<String>();
        while (cursor.moveToNext()){
            int IdTruyen = Integer.parseInt(cursor.getString(2));
            if (IdTruyen == IDtruyen){
                String TenTap = Integer.toString(cursor.getInt(1));
                items.add("Tập "+ TenTap);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        mlvChapter.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ShowTap();
    }
}
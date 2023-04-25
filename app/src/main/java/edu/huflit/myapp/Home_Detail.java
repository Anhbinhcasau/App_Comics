package edu.huflit.myapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.huflit.myapp.Model.Dialog_rating;
import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.database.dtbApp;

public class Home_Detail extends AppCompatActivity {
    Button mBtnSummary,  mBtnChapter, mBtnComment , mBtnContinue ;
    ImageButton mBntExt;
    TextView mTvSummary, tvNameComic, tvSummary,tvNameAuthor, tvCate;
    public ImageView mImgFavorite, mImgRating, imgMain;
    ListView mlvChapter ;
    ArrayList<TruyenTranh> arrayListTruyen;
    dtbApp dtbapp;

    boolean hidden = true;
    boolean isColor = false;
    int likeee = 0;
    String items[] = new String[] {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
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
        tvCate = findViewById(R.id.tvCategory);

        dtbapp = new dtbApp(this);
        TruyenTranh truyenTranh = new TruyenTranh();

        Intent intent = getIntent();
        String anh = intent.getStringExtra("anh");
        String ten = intent.getStringExtra("Ten");
        String theLoai = intent.getStringExtra("theLoai");
        String tomTat  = intent.getStringExtra("tomtat");
        String tacgia = intent.getStringExtra("tacgia");

        tvNameComic.setText(ten);
        tvSummary.setText(tomTat);
        tvNameAuthor.setText(tacgia);
        tvCate.setText(theLoai);
        Glide.with(this).load(anh).fitCenter().into(imgMain);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        mlvChapter.setAdapter(adapter);

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
                    likeee = 1;
                    isColor = false;
                }else {
                    mImgFavorite.setBackgroundResource(R.drawable.baseline_favorite_shadow);
                    likeee = 0;
                    isColor = true;
                }
                adapter.notifyDataSetChanged();
            }
        });
        mBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home_Detail.this, Read_Book.class );
                startActivity(i);
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
}
package edu.huflit.myapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.huflit.myapp.Model.Dialog_rating;

public class Home_Detail extends AppCompatActivity {
    Button mBtnSummary, mBtnFavorite, mBtnChapter, mBtnComment , mBtnContinue, mBtnRating;
    TextView mTvSummary;
    ListView mlvChapter ;

    RatingBar mRtBStar ;
    boolean hidden = true;
    boolean isColor = false;
    String items[] = new String[] {"chap1","chap2","chap3","chap1","chap2","chap3","chap2","chap3","chap1","chap2","chap3","chap2","chap3","chap1","chap2","chap3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        mBtnSummary = (Button) findViewById(R.id.btnSummary);
        mBtnChapter = (Button) findViewById(R.id.btnChapter);
        mBtnComment = (Button) findViewById(R.id.btnComment);
        mBtnFavorite = (Button) findViewById(R.id.btnFavorite);
        mBtnRating = (Button) findViewById(R.id.btnRating);
        mBtnContinue = (Button) findViewById(R.id.btnContinue);

        mTvSummary = (TextView) findViewById(R.id.tvSummary);

        mlvChapter = (ListView) findViewById(R.id.lvchap);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        mlvChapter.setAdapter(adapter);

        mBtnChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvSummary.setVisibility(View.INVISIBLE);
                mBtnComment.setVisibility(View.INVISIBLE);
                mlvChapter.setVisibility(View.VISIBLE);
                if (hidden) {
                    mBtnChapter.setBackgroundResource(R.drawable.primary_drawable);
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
        mBtnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isColor){
                    mBtnFavorite.setBackgroundResource(R.drawable.baseline_favorite_red);
                    isColor = false;
                }else {
                    mBtnFavorite.setBackgroundResource(R.drawable.baseline_favorite_shadow);
                    isColor = true;
                }
            }
        });
        mBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home_Detail.this, Read_Book.class );
                startActivity(i);
            }
        });
        mBtnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog_rating rating = new Dialog_rating(Home_Detail.this);
                rating.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
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
    }
}
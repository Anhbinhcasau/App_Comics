package edu.huflit.myapp.Model;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import edu.huflit.myapp.R;

public class Dialog_rating extends Dialog {
    private  float userRate = 0;
    public Dialog_rating(@NonNull Context context) {
        super(context);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_layout);
        final AppCompatButton saveRateBtn = findViewById(R.id.btnSaveRating);
        final AppCompatButton extRateBtn = findViewById(R.id.btnExtRating);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);

        saveRateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        extRateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}

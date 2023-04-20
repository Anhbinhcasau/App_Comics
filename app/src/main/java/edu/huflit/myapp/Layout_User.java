package edu.huflit.myapp;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import edu.huflit.myapp.database.dtbApp;

public class Layout_User extends AppCompatActivity {
    ImageView ava;
    ImageButton btnImg;
    EditText edtName, edtId, edtEmail;
    Button btnSave, btnUPa;
    ImageView imgAva;

    edu.huflit.myapp.database.dtbApp dtbApp;

    String email, tentaikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_user);

        ava = (ImageView) findViewById(R.id.imgAva);
        btnImg = (ImageButton) findViewById(R.id.imgBtnEdt);
        edtName = (EditText) findViewById(R.id.edtName);
        edtId = (EditText) findViewById(R.id.edtID);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnUPa= (Button) findViewById(R.id.btnUpAva);
        imgAva = (ImageView) findViewById(R.id.imgAva);

        dtbApp = new dtbApp(this);


        //Lay du lieu tu login
        Intent intent = getIntent();
        int pk = intent.getIntExtra("phanquyen",0);
        email = intent.getStringExtra("Email");
        tentaikhoan = intent.getStringExtra("TaiKhoan");
        Log.e("Test 1 " , pk +email+tentaikhoan);

        Cursor c = dtbApp.getData();
        while (c.moveToNext()) {
            Log.e("Test",c.getString(1) + c.getString(3)+ c.getInt(0));
            if (tentaikhoan==(c.getString(1)) ) {
                edtName.setText(c.getString(1));
                edtId.setText(c.getInt(0));
                edtEmail.setText(c.getString(3));
            }
            String tentk = c.getString(1);
            if(tentk==tentaikhoan) {
                edtName.setText(c.getString(1));
                edtId.setText(c.getInt(0));
                edtEmail.setText(c.getString(3));
            }
        }
        //Ẩn 2 button Up và Save ở chế độ xem
        btnSave.setVisibility(View.INVISIBLE);
        btnUPa.setVisibility(View.INVISIBLE);
        //Nhấn đổi chế độ Edit
        btnImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                edtName.setEnabled(true);
                edtName.setFocusable(false);
                edtName.setFocusableInTouchMode(true);
                edtEmail.setEnabled(true);
                edtEmail.setFocusable(true);
                edtEmail.setFocusableInTouchMode(true);


                int bg = getResources().getColor(R.color.red);
                btnImg.setBackgroundTintList(ColorStateList.valueOf(bg));

                btnSave.setVisibility(View.VISIBLE);
                btnUPa.setVisibility(View.VISIBLE);
            }
        });
        btnUPa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent anh = new Intent();
                anh.setType("image/*");
                anh.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(anh, "Chọn ảnh"), 1);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                btnSave.setVisibility(View.INVISIBLE);
                btnUPa.setVisibility(View.INVISIBLE);
                int bg = getResources().getColor(R.color.teal_200);
                btnImg.setBackgroundTintList(ColorStateList.valueOf(bg));

                edtName.setEnabled(false);
                edtName.setFocusable(false);

                edtEmail.setEnabled(false);
                edtEmail.setFocusable(false);


            }
        });
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = charSequence.toString();

                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    // Đây là kiểu email
                    edtEmail.setError(null);
                } else {
                    // Thông báo đây ko phải kiểu email
                    edtEmail.setError("Invalid email address");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @Override
    protected void onActivityResult(int request, int result, @Nullable Intent data)
    {
        super.onActivityResult(request, result, data);
        if (result == RESULT_OK && request == 1) {
            Uri uri = data.getData();
            imgAva.setImageURI(uri);
        }
    }
}
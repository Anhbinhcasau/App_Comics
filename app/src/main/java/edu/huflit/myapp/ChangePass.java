package edu.huflit.myapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import edu.huflit.myapp.Model.Users;
import edu.huflit.myapp.database.dtbApp;

public class ChangePass extends AppCompatActivity {
    EditText edtOldPass, edtNewPass, edtRePass;
    dtbApp dtbapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        edtOldPass = findViewById(R.id.edtOldPass);
        edtNewPass = findViewById(R.id.edtNewPass);
        edtRePass = findViewById(R.id.edtRepass);

        String name = getIntent().getStringExtra("username");

        String oldPass = edtOldPass.getText().toString();
        String newPass = edtNewPass.getText().toString();
        String RePass = edtRePass.getText().toString();
        Cursor cursor = dtbapp.getData();

//        while (cursor.moveToNext()){
//            String nameUser = cursor.getString(1);
//            String password = cursor.getString(2);
//            if(nameUser.equals(name)){
//                if(oldPass.equals(password)){
//                    dtbapp.ChangePass();
//                }
//            }
//        }

    }
//    private Users ChangePass(){
//
//    }
}
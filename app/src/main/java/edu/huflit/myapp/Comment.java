package edu.huflit.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.huflit.myapp.Model.List_Comment;
import edu.huflit.myapp.adapter.Comment_Adapter;

public class Comment extends AppCompatActivity {

    EditText edtComment;
    Button btnSend, btnExtComment;
    ListView lvComment;
    ArrayList arrComment;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        lvComment = findViewById(R.id.lVComment);
        btnSend = findViewById(R.id.btnSend);
        btnExtComment = findViewById(R.id.btnExitComment);
        edtComment = findViewById(R.id.edtComment);

        arrComment = new ArrayList<List_Comment>();
        adapter= new Comment_Adapter(this,R.layout.layout_comment,arrComment);

        lvComment.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cmt = edtComment.getText() + "";
                List_Comment emp = new List_Comment();
                emp.setComment(cmt);
                arrComment.add(emp);
                adapter.notifyDataSetChanged();
                edtComment.setText("");
            }
        });
        btnExtComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
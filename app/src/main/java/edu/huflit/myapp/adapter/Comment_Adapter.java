package edu.huflit.myapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.huflit.myapp.Model.List_Comment;
import edu.huflit.myapp.R;

public class Comment_Adapter extends ArrayAdapter {
    Activity context = null;
    ArrayList<List_Comment> myArray= null;
    int layoutId;

    public Comment_Adapter(Activity context, int layoutId,ArrayList<List_Comment> arr) {
        super(context, layoutId,arr);
        this.context = context;
        this.layoutId =layoutId;
        this.myArray = arr;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId,null);

        final TextView tvNameUser = (TextView) convertView.findViewById(R.id.tvNameUser);
        final TextView tvComment =  (TextView) convertView.findViewById(R.id.tvComment);
        final ImageView imgAvatarUser = (ImageView) convertView.findViewById(R.id.imgAvatarUser);

        List_Comment emp = myArray.get(position);
        // test
        tvNameUser.setText("Luong beo");

        //tvNameUser.setText(emp.getNameUser());
        tvComment.setText(emp.getComment());

        // them thu vien picassso de lay anh
        // Picasso.get().load(emp.getimgAvatar()).into(imgAvatarUser);
        // nay test thoi
        imgAvatarUser.setImageResource(R.drawable.doraemon);
        return convertView;
    }
}

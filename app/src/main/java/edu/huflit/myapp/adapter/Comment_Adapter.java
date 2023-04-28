package edu.huflit.myapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

        String image ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSIxKuXfYotFraCK2lF-hy9rJoTWlmZqtLbR-dln5dopVNTjcw_3H1MYAPVEzQdDrjecI&usqp=CAU" ;
        List_Comment emp = myArray.get(position);
        tvNameUser.setText(emp.getNameUser());
        tvComment.setText(emp.getComment());

        Glide.with(getContext())
                .load(image)
                .into(imgAvatarUser);
        return convertView;
    }
}

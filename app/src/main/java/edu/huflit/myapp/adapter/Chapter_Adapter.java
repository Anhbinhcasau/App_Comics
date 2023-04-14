package edu.huflit.myapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.huflit.myapp.Model.List_Chapter;
import edu.huflit.myapp.R;

public class Chapter_Adapter extends ArrayAdapter<List_Chapter> {
    Activity context = null;
    ArrayList<List_Chapter> myArray= null;
    int layoutId;
    public Chapter_Adapter(Activity context, int layoutId,ArrayList<List_Chapter>arr){
        super(context,layoutId,arr);
        this.context =context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId,null);

        TextView tvChapter = (TextView) convertView.findViewById(R.id.tvChapter);
        List_Chapter emp = myArray.get(position);
        tvChapter.setText(emp.getChapter());
        return convertView;
    }
}


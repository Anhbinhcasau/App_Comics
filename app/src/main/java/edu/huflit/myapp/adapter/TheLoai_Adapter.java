package edu.huflit.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.huflit.myapp.Model.TheLoaiTruyen;
import edu.huflit.myapp.R;

public class TheLoai_Adapter extends BaseAdapter {

    private Context context;

    private int layout;

    private List<TheLoaiTruyen> theLoaiList;

    public TheLoai_Adapter(Context context, int layout, List<TheLoaiTruyen> theLoaiList) {
        this.context = context;
        this.layout = layout;
        this.theLoaiList = theLoaiList;
    }

    @Override
    public int getCount() {
        return theLoaiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        ImageView img = (ImageView) view.findViewById(edu.huflit.myapp.R.id.imgTheLoai);
        TextView txt = (TextView) view.findViewById(R.id.tvTheLoai);
        TheLoaiTruyen type = theLoaiList.get(i);
        txt.setText(type.getTenTL());
        Glide.with(this.context).load(type.getHinhTL()).into(img);
        return view;

    }
}

package edu.huflit.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.R;

public class Like_Adapter extends ArrayAdapter<TruyenTranh> {

    private Context ct;
    private ArrayList<TruyenTranh> arrad;

    public Like_Adapter( Context context, int resource, List<TruyenTranh> objects) {
        super(context, resource, objects);
        this.ct=context;
        this.arrad = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null) {
            LayoutInflater inflater=(LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyen_yeuthich,null);
        }

        if(arrad.size()>0){
            TruyenTranh truyenTranh =this.arrad.get(position);

            TextView tenTruyen = convertView.findViewById(R.id.tvNameTruyenYT);
            ImageView imgTruyen = convertView.findViewById(R.id.imgTruyenYT);
            ImageView imgYeuThich = convertView.findViewById(R.id.imgFavoriteYt);

            tenTruyen.setText(truyenTranh.getTenTruyen());
            Glide.with(this.ct).load(truyenTranh.getLinkAnh()).into(imgTruyen);
            boolean isColor = false;
            imgYeuThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isColor){
                        imgYeuThich.setBackgroundResource(R.drawable.baseline_favorite_red);
                    }else {
                        imgYeuThich.setBackgroundResource(R.drawable.baseline_favorite_shadow);
                    }
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }
}

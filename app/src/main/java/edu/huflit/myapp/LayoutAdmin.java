package edu.huflit.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.adapter.AdminTruyenAdapter;
import edu.huflit.myapp.adapter.TruyenTranhAdapter;
import edu.huflit.myapp.database.dtbApp;

public class LayoutAdmin extends AppCompatActivity {
    Button btnThem;
    public static ListView listView;
    public static dtbApp dtbapp;
    ArrayList<TruyenTranh> tranhArrayList;
    AdminTruyenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_admin);
        dtbapp = new dtbApp(this);

        btnThem = findViewById(R.id.btnThemTruyen);
        listView = findViewById(R.id.lvQuanLy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LayoutAdmin.this, ThemTruyen.class);
                startActivity(i);
            }
        });
        Init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = dtbapp.getDataTruyen();
                if (cursor.moveToPosition(i)) {
                    // Di chuyển con trỏ đến vị trí item được chọn
                    int idTruyen = cursor.getInt(0);
                    String Ten = cursor.getString(1);
                    String tomtat = cursor.getString(2);
                    String theLoai = cursor.getString(3);
                    String anh = cursor.getString(4);
                    String tacgia = cursor.getString(5);
                    Intent a = new Intent(LayoutAdmin.this, Home_Detail.class);
                    a.putExtra("anh", anh);
                    a.putExtra("theLoai", theLoai);
                    a.putExtra("Id", idTruyen);
                    a.putExtra("Ten", Ten);
                    a.putExtra("tomtat", tomtat);
                    a.putExtra("tacgia", tacgia);
                    startActivity(a);
                }
                cursor.close();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LayoutAdmin.this);
                builder.setMessage("Bạn muốn xóa hay sửa truyện này?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Xóa item tại vị trí position trong danh sách truyện
                        dtbapp.Delete(tranhArrayList.get(i));
                        tranhArrayList.remove(i);
                        listView.invalidateViews();

                        // Cập nhật lại dữ liệu và giao diện
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                return false;
            }
        });
    }
    //Hiện các truyện
    private void Init() {
        Cursor cursor = dtbapp.getDataTruyen();
        tranhArrayList = new ArrayList<TruyenTranh>();

        while (cursor.moveToNext()){
            TruyenTranh truyenTranh= new TruyenTranh();
            int id = cursor.getInt(0);
            String Ten =cursor.getString(1);
            String anh = cursor.getString(4);
            truyenTranh.setIdTruyen(id);
            truyenTranh.setTenTruyen(Ten);
            truyenTranh.setLinkAnh(anh);
            tranhArrayList.add(truyenTranh);
        }
        cursor.moveToFirst();
        //Thực hiện khi không sử dụng
        cursor.close();
        adapter = new AdminTruyenAdapter(this,0,tranhArrayList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Init();
    }
}
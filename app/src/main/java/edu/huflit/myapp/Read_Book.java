package edu.huflit.myapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.myapp.Model.List_Chapter;
import edu.huflit.myapp.adapter.Chapter_Adapter;
import edu.huflit.myapp.adapter.Image_Adapter;

public class Read_Book extends AppCompatActivity {

    private RelativeLayout rlTopBar, rlBottomBar;

    private boolean hidden = true;

    TextView tvNovel;
    ListView lvComic, mlvChapter;
    Button btnShowChapter, btnExit;
    String items[] = new String[] {"chap1","chap2","chap3","chap1","chap2","chap3","chap2","chap3","chap1","chap2","chap3","chap2","chap3","chap1","chap2","chap3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://truyen-9f7f6.appspot.com/");
        StorageReference imageRef = storage.getReference().child("images");

        imageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
           @Override
           public void onSuccess(ListResult listResult) {

               List<StorageReference> imageRefs = listResult.getItems();
               List<Bitmap> bitmaps = new ArrayList<>();
               for (StorageReference imageRef : imageRefs) {
                   imageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                       @Override
                       public void onSuccess(byte[] bytes) {
                           Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                           bitmaps.add(bitmap);
                           if (bitmaps.size() == imageRefs.size()) {

                               Image_Adapter adapterHinh = new Image_Adapter(Read_Book.this, bitmaps);
                               lvComic.setAdapter(adapterHinh);
                           }
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception exception) {
                           // Xử lý bất kỳ lỗi nào
                       }
                   });
               }
           }
       }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Xử lý bất kỳ lỗi nào
            }
        });



        btnShowChapter = (Button) findViewById(R.id.btnShowChapter);
        btnExit = (Button) findViewById(R.id.btnExit);
        lvComic = findViewById(R.id.lvComic);
        rlTopBar = findViewById(R.id.rlTopBar);
        rlBottomBar= findViewById(R.id.rlBottomBar);
        mlvChapter = (ListView) findViewById(R.id.lvChapter);


        ArrayList arrChapter = new ArrayList<List_Chapter>();

        for (int i = 0; i < items.length; i++) {
            List_Chapter chapter = new List_Chapter(items[i]);
            arrChapter.add(chapter);
        }
        ArrayAdapter adapterChapter = new Chapter_Adapter(this, R.layout.item_custom_list_view_chapter, arrChapter);
        mlvChapter.setAdapter(adapterChapter);


        btnShowChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawerChapter = findViewById(R.id.drawer_chapter);
                drawerChapter.openDrawer((GravityCompat.END));
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvComic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private static final long DOUBLE_CLICK_TIME_DELTA = 300; //Thời gian giữa 2 lần click (0.3s)
            private long lastClickTime = 0; // Khởi tạo giá trị bằng 0
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // gán thời gian hiện tại
                long clickTime = System.currentTimeMillis();
                if(clickTime - lastClickTime <= DOUBLE_CLICK_TIME_DELTA){
                    if(hidden){
                        rlBottomBar.setVisibility(View.INVISIBLE);
                        rlTopBar.setVisibility(View.INVISIBLE);
                        hidden = false;
                    }else{
                        rlBottomBar.setVisibility(View.VISIBLE);
                        rlTopBar.setVisibility(View.VISIBLE);
                        hidden = true;
                    }
                }
                lastClickTime = clickTime;
            }
        });
    }

}
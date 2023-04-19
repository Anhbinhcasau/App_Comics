package edu.huflit.myapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import edu.huflit.myapp.Model.TruyenTranh;
import edu.huflit.myapp.Model.Users;

public class dtbApp extends SQLiteOpenHelper {
    // Bảng Tài Khoản
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    private static int VERSION = 1;

    // Bảng Truyện
    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String TAC_GIA = "tacgia";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";

    //Bảng Chapter
    private static String TABLE_TAP = "tap";
    private static String ID_TAP = "idTap";
    private static String TEN_TAP = "tenTap";

    //Bảng comment
    //ten tk, ten truyen, id truyen
    //Bảng yêu tích
    //ten truyen, id truyen
    //Bảng đánh giá
    //ten truyen, id truyen, nút ngôi sao


    // Phương thức tương tác với hệ điều hành truy cập vào tài nguyên hệ thống
    private Context context;


    public dtbApp(@Nullable Context context) {
        super(context, "App doc truyen", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Tạo bảng Tài Khoản
        String SQLQuery = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "
                +ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TEN_TAI_KHOAN+" TEXT UNIQUE, "
                +MAT_KHAU+" TEXT, "
                +EMAIL+" TEXT, "
                + PHAN_QUYEN+" INTEGER) ";

        //Tạo bảng Truyện
        String SQLQuery1 = "CREATE TABLE "+ TABLE_TRUYEN +" ( "
                +ID_TRUYEN+" integer PRIMARY KEY AUTOINCREMENT, "
                +TEN_TRUYEN+" TEXT UNIQUE, "
                +NOI_DUNG+" TEXT, "
                +IMAGE+" TEXT, "
                +TAC_GIA+" TEXT, "
                +ID_TAP+" INTEGER , FOREIGN KEY ( "
                +ID_TAP +" ) REFERENCES "
                +TABLE_TAP+"("
                +ID_TAP+"))";

        //Tạo bảng tập Truyện
        String SQLQuery2 = "CREATE TABLE "+ TABLE_TAP +" ( "
                + ID_TAP+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TEN_TAP+" TEXT UNIQUE, "
                +ID_TRUYEN+" INTEGER, FOREIGN KEY ("+ ID_TRUYEN +") REFERENCES "
                +TABLE_TRUYEN+"(" + ID_TRUYEN +"))";

        //Insert Dữ Liệu vảo bảng người dùng
        //Phân quyền ( 1 - admin ) ( 2 - user)
        String SQLQuery3 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','admin@gmail.com',1)";
        String SQLQuery4 = "INSERT INTO TaiKhoan VAlUES (null,'binh','binh','binh@gmail.com',2)";

        String SQLQuery5 = "INSERT INTO Truyen VALUES (null,'Doraemon','Vừa xem vừa ăn cơm thì hết sảy@@','https://i.pinimg.com/564x/7f/ac/10/7fac103e4a43eda31d5896e48cabf28c.jpg', 'Fujiko F. Fujio',1)";



        //Thực hiện các câu lệnh truy vấn không trả về kết quả
        //sqLiteDatabase.execSQL(SQLQuery);
        //sqLiteDatabase.execSQL(SQLQuery1);
        //sqLiteDatabase.execSQL(SQLQuery2);
        //sqLiteDatabase.execSQL(SQLQuery3);
        //sqLiteDatabase.execSQL(SQLQuery4);
        //sqLiteDatabase.execSQL(SQLQuery5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + dtbApp.TABLE_TAIKHOAN,null) ;
        return res;
    }

    public Cursor getDataTruyen(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + dtbApp.TABLE_TRUYEN,null) ;
        return res;
    }
    // Hàm add vào dtb khi user tạo tài khoản thành công
    public void Add(Users taikhoan){
        SQLiteDatabase dtb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN,taikhoan.getTenTaiKhoan());
        values.put(MAT_KHAU,taikhoan.getMatkhau());
        values.put(EMAIL,taikhoan.getEmail());
        values.put(PHAN_QUYEN,taikhoan.getPhanquyen());
        dtb.insert(TABLE_TAIKHOAN,null,values);
        dtb.close();
        Log.e("Add taikhoan ","Thành Công");
    }
    public void Addtruyen(TruyenTranh truyenTranh){
        SQLiteDatabase dtb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_TRUYEN,truyenTranh.getTenTruyen());
        values.put(ID_TRUYEN,truyenTranh.getIdTruyen());
        values.put(NOI_DUNG,truyenTranh.getTenChap());
        values.put(IMAGE,truyenTranh.getLinkAnh());
        values.put(TAC_GIA,truyenTranh.getTacGia());

        dtb.insert(TABLE_TRUYEN,null,values);
        dtb.close();
        Log.e("Add truyenTranh ","Thành Công");
    }
    public int Delete(int i){
        SQLiteDatabase db = this.getReadableDatabase();
        int res = db.delete(TABLE_TRUYEN, ID_TRUYEN + " = " + i,null);
        return res;
    }
}

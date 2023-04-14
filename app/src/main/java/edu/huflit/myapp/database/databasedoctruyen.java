package edu.huflit.myapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databasedoctruyen extends SQLiteOpenHelper {

    //Tên database
    private static String DATABASE_NAME = "doctruyen";

    //Các biến của tài khoản
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";

    private static int VERSION = 1;

    //Các biến để thêm truyện mới
    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";

    // Phương thức tương tác với hệ điều hành truy cập vào tài nguyên hệ thống
    private Context context;

    //Bảng tài khoản
    private String SQLQuery = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";

    //Bảng thêm truyện mới
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TRUYEN +" ( "+ID_TRUYEN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TRUYEN+" TEXT UNIQUE, "
            +NOI_DUNG+" TEXT, "
            +IMAGE+" TEXT, "+ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+"))";


    //Insert Dữ Liệu vảo bảng người dùng
    //Phân quyền ( 1 - admin ) ( 2 - user)
    private String SQLQuery2 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','admin@gmail.com',2)";
    private String SQLQuery3 = "INSERT INTO TaiKhoan VAlUES (null,'khanh','khanh','khanh@gmail.com',1)";

    //Tạo bảng tại phương thức này
    public databasedoctruyen(@Nullable Context context) {
        super(context, DATABASE_NAME, null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Thực hiện các câu lệnh truy vấn không trả về kết quả
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getData() {
        SQLiteDatabase dtb = this.getReadableDatabase();
        Cursor rel = dtb.rawQuery("SELECT * FROM "+TABLE_TAIKHOAN,null);
        return rel;
    }
}

package edu.huflit.myapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import edu.huflit.myapp.Model.List_Comment;
import edu.huflit.myapp.Model.TapTruyen;
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


    //Bảng Comment
    private static String TABLE_COMMENT = "comment";
    private static String ID_COMMENT = "idComment";
    private static String NOI_DUNG_COMMENT = "noiDungComment";

    //Bảng yêu tích
    private static String TABLE_LIKE = "tblike";
    private static String ID_LIKE = "idLike";
    private static String LIKE = "_like";

    //Bảng đánh giá
    private static String TABLE_RATING = "tbrating";
    private static String ID_RATING = "idRating";
    private static String ISRATING= "_rating";



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
                +TAC_GIA+" TEXT)";


        //Tạo bảng tập Truyện
        String SQLQuery2 = "CREATE TABLE "+ TABLE_TAP +" ( "
                + ID_TAP+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TEN_TAP+" INTEGER, "
                +ID_TRUYEN+" INTEGER, FOREIGN KEY ("+ ID_TRUYEN +") REFERENCES "
                +TABLE_TRUYEN+"(" + ID_TRUYEN +"))";

        //Tạo bảng Comment
        String SQLQuery3 = "CREATE TABLE "+ TABLE_COMMENT +" ( "
                +ID_COMMENT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NOI_DUNG_COMMENT+" TEXT , "
                +ID_TRUYEN + " INTEGER , "
                +TEN_TAI_KHOAN +" TEXT ,  "
                +"FOREIGN KEY ("+ ID_TRUYEN +") REFERENCES " +TABLE_TRUYEN+"(" + ID_TRUYEN +"),"
                +"FOREIGN KEY ("+ TEN_TAI_KHOAN +") REFERENCES " +TABLE_TAIKHOAN+"(" + TEN_TAI_KHOAN +"))";

        //Tạo bảng yêu thích
        String SQLQuery4 = "CREATE TABLE "+ TABLE_RATING +" ( "
                +ID_RATING+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ISRATING + " INTEGER, "
                +ID_TRUYEN+" INTEGER, "
                +ID_TAI_KHOAN+ " INTEGER, "
                +"FOREIGN KEY ("+ ID_TRUYEN +") REFERENCES " +TABLE_TRUYEN+"(" + ID_TRUYEN +"),"
                +"FOREIGN KEY ("+ ID_TAI_KHOAN +") REFERENCES " +TABLE_TAIKHOAN+"(" + ID_TAI_KHOAN +"))";

        //Tạo bảng yêu thích
        String SQLQuery5 = "CREATE TABLE "+ TABLE_LIKE +" ( "
                +ID_LIKE+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +LIKE + " INTEGER, "
                +ID_TRUYEN+" INTEGER, "
                +ID_TAI_KHOAN+ " INTEGER, "
                +"FOREIGN KEY ("+ ID_TRUYEN +") REFERENCES " +TABLE_TRUYEN+"(" + ID_TRUYEN +"),"
                +"FOREIGN KEY ("+ ID_TAI_KHOAN +") REFERENCES " +TABLE_TAIKHOAN+"(" + ID_TAI_KHOAN +"))";

        //Insert Dữ Liệu vảo bảng người dùng
        //Phân quyền ( 1 - admin ) ( 2 - user)
        String SQLQuery6 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','admin@gmail.com',1)";
        String SQLQuery7 = "INSERT INTO TaiKhoan VAlUES (null,'binh','binh','binh@gmail.com',2)";

        String SQLQuery8 = "INSERT INTO Truyen VALUES (1,'Doraemon','Vừa xem vừa ăn cơm thì hết sảy@@','https://i.pinimg.com/564x/7f/ac/10/7fac103e4a43eda31d5896e48cabf28c.jpg', 'Fujiko F. Fujio')";
        String SQLQuery9 = "INSERT INTO Tap VALUES(null,1,1)";
        String SQLQuery10 = "INSERT INTO Tap VALUES(null,2,1)";
        String SQLQuery11 = "INSERT INTO Tap VALUES(null,3,1)";
        String SQLQuery12 = "INSERT INTO Tap VALUES(null,4,1)";
        String SQLQuery13 = "INSERT INTO Truyen VALUES (0,'Conan','Vừa xem vừa ăn cơm thì hết sảy@@','https://st.nettruyenvt.com/data/comics/30/tham-tu-conan.jpg', 'Fujiko F. Fujio')";
        String SQLQuery14 = "INSERT INTO Tap VALUES(null,1,0)";
        String SQLQuery15 = "INSERT INTO Tap VALUES(null,2,0)";
        String SQLQuery16 = "INSERT INTO Tap VALUES(null,3,0)";
        String SQLQuery17 = "INSERT INTO Tap VALUES(null,4,0)";



        //Thực hiện các câu lệnh truy vấn không trả về kết quả
        sqLiteDatabase.execSQL(SQLQuery);
        sqLiteDatabase.execSQL(SQLQuery1);
        sqLiteDatabase.execSQL(SQLQuery2);

        sqLiteDatabase.execSQL(SQLQuery3);
        sqLiteDatabase.execSQL(SQLQuery4);
        sqLiteDatabase.execSQL(SQLQuery5);


        sqLiteDatabase.execSQL(SQLQuery6);
        sqLiteDatabase.execSQL(SQLQuery7);
        sqLiteDatabase.execSQL(SQLQuery8);
        sqLiteDatabase.execSQL(SQLQuery9);
        sqLiteDatabase.execSQL(SQLQuery10);
        sqLiteDatabase.execSQL(SQLQuery11);
        sqLiteDatabase.execSQL(SQLQuery12);
        sqLiteDatabase.execSQL(SQLQuery13);
        sqLiteDatabase.execSQL(SQLQuery14);
        sqLiteDatabase.execSQL(SQLQuery15);
        sqLiteDatabase.execSQL(SQLQuery16);
        sqLiteDatabase.execSQL(SQLQuery17);

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
    public Cursor getDataTap(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + dtbApp.TABLE_TAP,null) ;
        return res;
    }
    public Cursor getDataComment(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + dtbApp.TABLE_COMMENT,null) ;
        return res;
    }
    // Them User
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
    //Thêm truyện mới
    public void Addtruyen(TruyenTranh truyenTranh){
        SQLiteDatabase dtb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_TRUYEN,truyenTranh.getTenTruyen());
        values.put(NOI_DUNG,truyenTranh.getNoiDungTruyen());
        values.put(IMAGE,truyenTranh.getLinkAnh());
        values.put(TAC_GIA,truyenTranh.getTacGia());

        dtb.insert(TABLE_TRUYEN,null,values);
        dtb.close();
        Log.e("Add truyenTranh ","Thành Công");
    }

    //Them tap moi
    public void Addtap(TapTruyen tapTruyen){
        SQLiteDatabase dtb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_TAP, tapTruyen.getTenTap());
        values.put(ID_TAP, tapTruyen.getId());
        values.put(ID_TRUYEN, tapTruyen.getIdTruyen());
        dtb.insert(TABLE_TAP, null, values);
        dtb.close();
        Log.e("Add tapTruyen", "Thành Công" );
    }

    //Xoa truyen ( Check ID trong table truyen de xoa truyen )
    public void Delete(TruyenTranh truyenTranh){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_TRUYEN, ID_TRUYEN + "=" + "'" + truyenTranh.getIdTruyen() + "'",null);
        db.close();
    }
    public void DeleteCmt(int commentId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COMMENT, ID_COMMENT + "=?", new String[]{String.valueOf(commentId)});
        db.close();
    }


    //Update lai truyen moi
    public void Edit(TruyenTranh truyenTranh){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_TRUYEN, truyenTranh.getTenTruyen());
        values.put(TAC_GIA, truyenTranh.getTacGia());
        values.put(NOI_DUNG, truyenTranh.getNoiDungTruyen());
        values.put(IMAGE,truyenTranh.getLinkAnh());
        db.update(TABLE_TRUYEN, values,ID_TRUYEN + " = " + truyenTranh.getIdTruyen(), null);
        db.close();
    }

    //Thay doi mat khau
    public void ChangePass(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MAT_KHAU,users.getMatkhau());
        db.update(TABLE_TAIKHOAN, values,ID_TAI_KHOAN + " = " + users.getmId(), null);
        db.close();
    }

    //Lay tap co ID truyen tuong ung
    public Cursor getDataTapByIDTruyen(int IDtruyen) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {ID_TAP, TEN_TAP, ID_TRUYEN};
        String selection = "idtruyen = ?"; //Check ID
        String[] selectionArgs = {String.valueOf(IDtruyen)};
        Cursor cursor = db.query(TABLE_TAP, columns, selection, selectionArgs, null, null, null);
        return cursor;
    }
    public String getPasswordById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {MAT_KHAU};
        String selection = "idtaikhoan = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_TAIKHOAN, columns, selection, selectionArgs, null, null, null);
        String password = null;
        if (cursor.moveToFirst()) {
            int passwordIndex = cursor.getColumnIndex(MAT_KHAU);
            password = cursor.getString(passwordIndex);
        }
        cursor.close();
        return password;
    }
    public void comment(List_Comment listComment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN, listComment.getNameUser());
        values.put(ID_TRUYEN, listComment.getIdTruyen());
        values.put(NOI_DUNG_COMMENT, listComment.getComment());
        db.insert(TABLE_COMMENT, null, values);
        db.close();
    }
    public Cursor getDataCommentdById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {ID_COMMENT,NOI_DUNG_COMMENT, TEN_TAI_KHOAN};
        String selection = "idtruyen = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_COMMENT, columns, selection, selectionArgs, null, null, null);
        return cursor;
    }


}

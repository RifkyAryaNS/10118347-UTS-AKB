package com.example.rifkyans10118347;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    public static final String nama_database = "db_notes";
    public static final String nama_table = "tabel_notes";

    public static final String row_id = "_id";
    public static final String row_judul = "judul";
    public static final String row_category = "category";
    public static final String row_isi = "isi";

    private SQLiteDatabase db;

    public DBHandler(Context context){
        super(context, nama_database, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + nama_table + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_judul + " TEXT, " + row_category + " TEXT, " + row_isi + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il){
        db.execSQL("DROP TABLE IF EXISTS " +nama_table);
    }

    public Cursor semuaData(){
        Cursor cursor = db.rawQuery("SELECT * FROM " +nama_table, null);
        return cursor;
    }

    public Cursor satuData(long id){
        Cursor cursor = db.rawQuery("SELECT * FROM " +nama_table+ " WHERE " +row_id, null);
        return cursor;
    }

    public void createData(ContentValues values){
        db.insert(nama_table, null, values);
    }

    public void updateData(ContentValues values, long id){
        db.update(nama_table, values, row_id + "=" + id, null);
    }

    public void deleteData(long id){

        db.delete(nama_table, row_id + "=" + id, null);
    }
}

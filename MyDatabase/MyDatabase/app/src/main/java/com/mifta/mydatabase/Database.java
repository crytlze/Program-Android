package com.mifta.mydatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mahasiswa.db";
    private static final int DATABASE_VERSION = 1;
    public Database (Context context ){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table mahasiswa(nim integer primary key, nama text null, jurusan text null," +
                " jk text null,  ttl text null)";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = " INSERT INTO mahasiswa (nim, nama, jurusan, jk, ttl) VALUES ('17020101', 'mocy', 'D3MI', 'Perempuan', 'Lampung');" ;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

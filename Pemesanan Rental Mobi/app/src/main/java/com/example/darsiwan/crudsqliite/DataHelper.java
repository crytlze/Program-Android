package com.example.darsiwan.crudsqliite;




import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rental.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table pemesanan(no integer primary key, nama text null, tgl text null, tlp text null, alamat text null, merk text null,lama text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO pemesanan (no, nama, tgl, tlp, alamat, merk, lama) VALUES ('1', 'Elin', '2018-07-12', '02545252366','Kebumen','Alphard','3 hari');";
        db.execSQL(sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
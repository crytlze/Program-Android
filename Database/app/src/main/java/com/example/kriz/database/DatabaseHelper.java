package com.example.kriz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mahasiswa.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void createTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS gejala");
        db.execSQL("CREATE TABLE if not exists gejala (id TEXT PRIMARY KEY, nama TEXT, " +
                "ya VARCHAR(5), tidak VARCHAR(5), mulai VARCHAR(5), selesai VARCHAR(5));");
    }

    public void generateData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL("INSERT INTO gejala VALUES ('G001','Berkeringat Pada Malam Hari', 'G002', 'G014', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G002','Tubuh terasa lemas', 'G003', 'G006', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G003','nafsu makan berkurang', 'G017', 'G004', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G004','bersin bersin', 'G005', 'G005', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G005','hidung tersumbat', 'G007', 'G007', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G006','tenggorokan kering', 'G021', 'G021', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G007','hidung berlendir', 'G016', 'G016', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G008','sering BAB', 'G009', 'G015', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G009','BAB nya encer / mencret', 'P003', 'P003', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G011','terlalu banyak makan', 'G012', 'G001', 'Y', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G012','Memakan makanan yang asing ', 'G013', 'G013', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G013','mual mual ', 'P004', 'P004', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G014','Pusing', 'G008', 'G020', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G015','Sakit perut ', 'G016', 'G016', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G016','mengigil', 'G018', 'G018', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G017','Tubuh Panas', 'P001', 'P001', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G018','Muncul Bintik Merah', 'G019', 'G019', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G019','Gatal gatal', 'P005', 'P005', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G020','sehat', 'G001', 'B', 'T', 'T');");
        db.execSQL("INSERT INTO gejala VALUES ('G021','Sering mengeluarkan suara Uhuk Uhuk dari mulut', 'P006', 'P006', 'T', 'T');");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.example.kriz.crud;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TambahData extends AppCompatActivity {

    Cursor cursor;
    Database database;
    EditText  text1,text2,text3,text4,text5;
    Button tambah2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        database = new Database(this);

        text1 = (EditText)findViewById(R.id.nim);
        text2 = (EditText)findViewById(R.id.nama);
        text3 = (EditText)findViewById(R.id.jurusan);
        text4 = (EditText)findViewById(R.id.jk);
        text5 = (EditText)findViewById(R.id.alamat);

        tambah2 = (Button)findViewById(R.id.simpan);

        tambah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("INSERT INTO mahasiswa (nim, nama, jurusan, jk, alamat) VALUES ('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "','" +
                        text3.getText().toString() + "','" +
                        text4.getText().toString() + "','" +
                        text5.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "SELAMAT DATA TELAH DIMASUKKAN", Toast.LENGTH_LONG).show();
            }
        });










    }
}

package com.example.kriz.crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahBiodata extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    Button simpan;
    private EditText nim, nama, jurusan, kelamin, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_biodata);

        nim = (EditText)findViewById(R.id.nim);
        nama = (EditText)findViewById(R.id.nama);
        jurusan = (EditText)findViewById(R.id.jurusan);
        kelamin = (EditText)findViewById(R.id.kelamin);
        alamat = (EditText)findViewById(R.id.alamat);

        database = new Database(this);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            nim.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            jurusan.setText(cursor.getString(2).toString());
            kelamin.setText(cursor.getString(3).toString());
            alamat.setText(cursor.getString(4).toString());
        }

        simpan = (Button)findViewById(R.id.simpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("update mahasiswa set nama='"+
                        nama.getText().toString() +"', jurusan='" +
                        jurusan.getText().toString()+"', jk='"+
                        kelamin.getText().toString() +"', alamat='" +
                        alamat.getText().toString() + "' where nim='" +
                        nim.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "DATA BERHASIL DI UBAH!!!!", Toast.LENGTH_LONG).show();
                LihatData.ma.RefreshList();
                finish();
            }
        });

    }
}

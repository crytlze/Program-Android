package com.example.kriz.crud;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    Menu menu;
    protected Cursor cursor;


    Database database;
    TextView text1,text2,text3,text4,text5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView11);
        text2 = findViewById(R.id.textView22);
        text3 = findViewById(R.id.textView33);
        text4 = findViewById(R.id.textView44);
        text5 = findViewById(R.id.textView55);



        database = new Database(this);
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama = '"+ getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
        }

        Button tambah = (Button)findViewById(R.id.tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tambah = new Intent(MainActivity.this, TambahData.class);
                startActivity(tambah);
            }
        });

        Button LihatData = (Button)findViewById(R.id.lihatdata);
        LihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lihat = new Intent(MainActivity.this, LihatData.class);
                startActivity(lihat);
            }
        });
    }


}

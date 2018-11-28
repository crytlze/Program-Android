package com.mifta.mydatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Cursor cursor;
    Database database;

    TextView text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this);

        text1 = (TextView) findViewById(R.id.text11);
        text2 = (TextView) findViewById(R.id.text22);
        text3 = (TextView) findViewById(R.id.text33);
        text4 = (TextView) findViewById(R.id.text44);
        text5 = (TextView) findViewById(R.id.text55);

        SQLiteDatabase dt = database.getReadableDatabase();

        cursor = dt.rawQuery("SELECT * FROM mahasiswa WHERE nama ='mocy'",null);
        cursor.moveToFirst();
        if(cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());


        }
    }
}

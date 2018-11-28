package com.example.darsiwan.crudsqliite;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LihatBiodata extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton2;
    TextView  no, nama, alamat, tanggal, nohp, merk, lama ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_biodata);

        dbHelper = new DataHelper(this);
        no = (TextView) findViewById(R.id.textView1);
        nama = (TextView) findViewById(R.id.textView2);
        tanggal = (TextView) findViewById(R.id.textView3);
        nohp = (TextView) findViewById(R.id.textView4);
        alamat = (TextView) findViewById(R.id.textView5);
        merk = (TextView) findViewById(R.id.textView6);
        lama = (TextView) findViewById(R.id.textView7);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pemesanan WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            no.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            tanggal.setText(cursor.getString(2).toString());
            nohp.setText(cursor.getString(3).toString());
            alamat.setText(cursor.getString(6).toString());
            merk.setText(cursor.getString(5).toString());
            lama.setText(cursor.getString(4).toString());
        }
        ton2 = (Button) findViewById(R.id.button1);
        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }



}
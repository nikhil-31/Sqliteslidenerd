package com.example.nikhil.sqliteslidenerd;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    NikHelper nikHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nikHelper = new NikHelper(this);
        SQLiteDatabase sqLiteDatabase= nikHelper.getWritableDatabase();




    }
}

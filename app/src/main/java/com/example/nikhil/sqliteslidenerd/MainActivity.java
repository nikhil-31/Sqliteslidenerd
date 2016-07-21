package com.example.nikhil.sqliteslidenerd;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    NikHelper nikHelper;
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username= (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);


        //My implementation of the helper
        nikHelper = new NikHelper(this);





    }
    public void addUser(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();

        //SQLiteDatabase object that represents the actual database.
        SQLiteDatabase sqLiteDatabase= nikHelper.getWritableDatabase();

    }
}

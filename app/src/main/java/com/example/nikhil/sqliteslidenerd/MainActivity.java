package com.example.nikhil.sqliteslidenerd;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


//Changed Git Global email id so now i can commit as nikhil-31. Learnt something new.
public class MainActivity extends AppCompatActivity {
    NikDatabaseAdapter nikDatabaseAdapter;
    EditText username;
    EditText password;
    EditText details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username= (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        details = (EditText) findViewById(R.id.editText3);

        //My implementation of the helper
        nikDatabaseAdapter = new NikDatabaseAdapter(this);





    }
    public void addUser(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();

        long id =nikDatabaseAdapter.insertData(user,pass);
        if(id<0){
            Toast.makeText(this,"unsuccessful",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Successfully added Username and Password",Toast.LENGTH_LONG).show();
        }
    }


    public void viewDetails(View view){
        String data = nikDatabaseAdapter.getAllData();
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }
    public void getDetails(View v){
        String s1 = details.getText().toString();
        String s2 = nikDatabaseAdapter.getData(s1);
        Toast.makeText(this,s2,Toast.LENGTH_LONG).show();
    }
}

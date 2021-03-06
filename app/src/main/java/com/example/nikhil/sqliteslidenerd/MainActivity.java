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
    //To add a new user.
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

    //Method to get all the data in the database
    public void viewDetails(View view){
        String data = nikDatabaseAdapter.getAllData();
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }
    //Method to get the password given a valid username
    public void getDetails(View v){
        String s1 = details.getText().toString();
        String s2 = nikDatabaseAdapter.getData(s1);
        Toast.makeText(this,s2,Toast.LENGTH_LONG).show();
    }
    //Method to update the data in the database
    public void update(View view){
        nikDatabaseAdapter.updateName("test","nikz");
    }

    //Method to delete the data in the database
    public void delete(View v){
        int count = nikDatabaseAdapter.deleteRow();


    }
}

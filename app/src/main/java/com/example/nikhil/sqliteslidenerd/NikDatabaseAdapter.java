package com.example.nikhil.sqliteslidenerd;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by nikhil on 16-07-2016.
 */







//Changed Git Global email id so now i can commit as nikhil-31
public class NikDatabaseAdapter{

    NikHelper helper;
    Context c;
    public NikDatabaseAdapter(Context context){
        this.c =context;
        helper= new NikHelper(context);

    }
    public long insertData(String name,String password){
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NikHelper.NAME,name);
        contentValues.put(NikHelper.PASSWORD,password);

        long id= db.insert(NikHelper.TABLE_NAME, null, contentValues);
        return id;
    }



    static class NikHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "nikdatabase";
        private static final String TABLE_NAME = "niktable";
        private static final int DATABASE_VERSION = 8;
        private static final String UID ="_id";
        private static final String NAME = "NAME";
        private static final String PASSWORD = "password";
        private static final String CREATE_TABLE= "CREATE TABLE "+TABLE_NAME+"( "+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+ " varchar(255),"+PASSWORD+" varchar(255)); ";
        private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public NikHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
            Toast.makeText(context,"constructor called",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                Toast.makeText(context,"onCreate called",Toast.LENGTH_LONG).show();
                db.execSQL(CREATE_TABLE);
            }
            catch(SQLException e){
                Message.message(context," "+ e);

            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
                Toast.makeText(context,"onUpgrade called",Toast.LENGTH_LONG).show();
            }
            catch (SQLException e){
                Message.message(context," "+e);
            }

        }
    }


}

package com.example.nikhil.sqliteslidenerd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public NikDatabaseAdapter(Context context){

        helper= new NikHelper(context);

    }
    /* Class to insert data into the database, It is required as we should not expose the database
    * name,table name,coloumn name as someone could destroy the database with access to the activity*/

    public long insertData(String name,String password){
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NikHelper.NAME,name);
        contentValues.put(NikHelper.PASSWORD,password);

        long id= db.insert(NikHelper.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    public String getAllData(){
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {NikHelper.UID,NikHelper.NAME,NikHelper.PASSWORD};
        Cursor cursor = db.query(NikHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){


            int cid = cursor.getInt(0);
            String name = cursor.getString(1);
            String password = cursor.getString(2);
            stringBuffer.append(cid+" "+name+" "+password+"\n");

        }
        return stringBuffer.toString();
    }

    public String getData(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        //select name,password from niktable where name='nikhil';
        String[] columns = {NikHelper.NAME,NikHelper.PASSWORD};
        //in the 3 spot the selection we writ the last part of the sql query (name='name')
        Cursor cursor = db.query(NikHelper.TABLE_NAME, columns, NikHelper.NAME+" = '"+name+"'", null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){


            int index1 = cursor.getColumnIndex(NikHelper.NAME);
            int index2 = cursor.getColumnIndex(NikHelper.PASSWORD);
            String person = cursor.getString(index1);
            String password = cursor.getString(index2);
            stringBuffer.append(person+" "+password+"\n");

        }
        return stringBuffer.toString();
    }
    public void updateName(String oldName ,String newName){
        //UPDATE NIKTABLE SET NAME = 'NIK' WHERE NAME =? (name in whereargs);

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(NikHelper.NAME, newName);
        String whereargs[] = {oldName};
        db.update(NikHelper.TABLE_NAME,contentValues,NikHelper.NAME+" =? ",whereargs);
    }
    public void deleteRow(){

    }




    // It is the helper class that extends sqliteopenhelper superclass.
    //It is static as it in java we give stuff the least preference.
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

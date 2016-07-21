package com.example.nikhil.sqliteslidenerd;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by nikhil on 16-07-2016.
 */
public class Message {
    public static void message(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

}

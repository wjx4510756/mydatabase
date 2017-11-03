package com.example.mydatabase.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 11070562 on 2017/11/3.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    private Context mContext;
    public static final String CREATE_TABLE = "create table my_table(" +
            "id integer primary key autoincrement," +
            "name text," +
            "age integer," +
            "homeland text)";


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

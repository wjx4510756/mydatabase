package com.example.mydatabase.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mydatabase.Bean;
import com.example.mydatabase.MyApplication;
import com.example.mydatabase.db.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11070562 on 2017/11/3.
 */

public class MyDatabase implements IDatabase {

    private static final String DB_NAME = "test.db";
    private static final int VERSION = 1;
    private SQLiteDatabase db;
    private static Context mContext;

    public static final String TABLE = "my_table";

    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String HOMELAND = "homeland";
    public static final String ALL = "all";


    private MyDatabase(Context context) {
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context, DB_NAME, null, VERSION);
        db = helper.getWritableDatabase();
    }

    public static MyDatabase getInstance() {
        mContext = MyApplication.getContext();
        return MyDatabaseHolder.sInstance;
    }

    /**
     * 静态内部类实现单例模式
     */
    private static class MyDatabaseHolder {
        private static final MyDatabase sInstance = new MyDatabase(mContext);
    }

    @Override
    public void saveData(Bean bean) {

        ContentValues values = new ContentValues();
        values.put("name", bean.getName());
        values.put("homeland", bean.getHomeland());
        values.put("age", bean.getAge());
        db.insert(TABLE, null, values);

    }

    @Override
    public List<Bean> queryData(String name) {
        List<Bean> beans = new ArrayList<>();

        Cursor cursor = null;
        try {
            if ("".equals(name)) {
                cursor = db.query(TABLE, null, null, null, null, null, null);
            } else {
                cursor = db.query(TABLE, null, "name=?", new String[]{name}, null, null, null);
            }
            if (cursor.moveToFirst()) {
                do {
                    Bean bean = new Bean();
                    bean.setName(cursor.getString(cursor.getColumnIndex("name")));
                    bean.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                    bean.setHomeland(cursor.getString(cursor.getColumnIndex("homeland")));
                    beans.add(bean);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return beans;
    }

    @Override
    public void changeData(String name, String values, String type) {

//
//        if (queryData(name) == null) {
//            return;
//        }

        ContentValues v = new ContentValues();
        if (AGE.equals(type)) {
            v.put(type, Integer.valueOf(values));
        }
        v.put(type, values);
        db.update(TABLE, v, "name=?", new String[]{name});
    }

    @Override
    public void deleteData(String name) {
        db.delete(TABLE, "name=?", new String[]{name});
    }
}



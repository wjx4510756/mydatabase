package com.example.mydatabase;

import android.app.Application;
import android.content.Context;

/**
 * Created by 11070562 on 2017/11/3.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}



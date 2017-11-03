package com.example.mydatabase.presenter;

import android.os.Handler;

import com.example.mydatabase.Bean;
import com.example.mydatabase.model.IDatabase;
import com.example.mydatabase.model.MyDatabase;
import com.example.mydatabase.view.IView;

import java.util.List;

/**
 * Created by 11070562 on 2017/11/3.
 */

public class MainPresenter {

    private IDatabase database;
    private IView view;


    private Handler handler;

    public MainPresenter(IView view) {
        this.view = view;
        database = MyDatabase.getInstance();
        handler = new Handler();
    }

    public void save() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.saveData(view.getBean());
                view.doClear();
                query();
            }
        }).start();
    }

    public void delete() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.deleteData(view.getName());
                view.doClear();
                try {      //删除操作比较耗时？？？
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                query();
            }
        }).start();
    }

    public void update() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                database.changeData(view.getName(), view.getHomeland(), "homeland");
                view.doClear();
                query();
            }
        }).start();
    }

    public void query() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Bean> list = database.queryData(view.getName());
                view.updateList(list);
            }
        }).start();

    }

}

package com.example.mydatabase.model;

import com.example.mydatabase.Bean;

import java.util.List;

/**
 * Created by 11070562 on 2017/11/3.
 */

public interface IDatabase {

    void deleteData(String name);

    void changeData(String name, String values, String type);

    List<Bean> queryData(String name);

    void saveData(Bean bean);


}

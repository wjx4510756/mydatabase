package com.example.mydatabase.view;

import com.example.mydatabase.Bean;

import java.util.List;

/**
 * Created by 11070562 on 2017/11/3.
 */

public interface IView {

    Bean getBean();

    void updateList(List<Bean> list);

    String getName();

    String getHomeland();

    String getAge();

    void doClear();

}

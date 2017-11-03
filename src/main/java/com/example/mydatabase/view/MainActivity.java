package com.example.mydatabase.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mydatabase.Bean;
import com.example.mydatabase.R;
import com.example.mydatabase.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IView {

    private List<Bean> mList;

    private ArrayAdapter<String> adapter;

    private List<String> list;

    private MainPresenter presenter;

    private Handler handler;

    private ListView mListView;
    private EditText name;
    private EditText age;
    private EditText homeland;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();

    }

    private void initData() {

        presenter = new MainPresenter(this);
        handler = new Handler();

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("item" + i);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.id_listView);
        name = (EditText) findViewById(R.id.id_name);
        age = (EditText) findViewById(R.id.id_age);
        homeland = (EditText) findViewById(R.id.id_homeland);

        mListView.setAdapter(adapter);
    }

    public void doClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.id_save:
                if ("".equals(getName()) || "".equals(getAge()) || "".equals(getHomeland())) {
                    Toast.makeText(this, "you should input completely", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.save();
                break;
            case R.id.id_delete:
                if ("".equals(getName())) {
                    Toast.makeText(this, "you should input name", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.delete();
                break;
            case R.id.id_update:
                if ("".equals(getName()) || "".equals(getHomeland())) {
                    Toast.makeText(this, "you should input name and homeland completely", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.update();
                break;
            case R.id.id_query:
                presenter.query();
                break;
            default:
                break;
        }
    }

    @Override
    public Bean getBean() {

        Bean bean = new Bean();
        bean.setName(name.getText().toString());
        bean.setAge(Integer.valueOf(age.getText().toString()));
        bean.setHomeland(homeland.getText().toString());

        return bean;
    }

    @Override
    public void updateList(final List<Bean> beans) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                mList = beans;
                list.clear();
                for (Bean bean : mList) {
                    list.add("name:" + bean.getName() + "   age:" + bean.getAge() + "    homeland:" + bean.getHomeland());
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "update database succeed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public String getHomeland() {
        return homeland.getText().toString();
    }

    @Override
    public String getAge() {
        return age.getText().toString();
    }

    @Override
    public void doClear() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                name.setText("");
                age.setText("");
                homeland.setText("");
            }
        });
    }
}

package com.gpf.myprojectysdq.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/11/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle();
        setContentView(setLayout());
        initView();
        initData();
        getMethod();
        setListener();
    }

    // 抽象的方法子类必须实现
    public abstract int setLayout();// 设置布局
    public abstract void initView();
    public void getMethod(){}
    // 非抽象的方法，子类可以选择性的实现
    public void initData(){}
    public void setListener(){}
    public void setStyle(){}
}

package com.gpf.myprojectysdq.base;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * 自定义的页面基类，在构造方法中调用创建使徒的方法，该方法由孩子强行实现
 * initView():创建视图
 * initData():初始化数据
 *
 */

public abstract class BasePager implements Serializable {

    public final Context context;
    public View rootView;
    public boolean initData;// 防止页面重复初始化的标记

    public BasePager(Context context){
        this.context = context;
        rootView = initView();
    }

    public abstract View initView();// 孩子必须实现
    public void initData(){}// 孩子选择性的实现,连网请求数据或者绑定数据的时候

}

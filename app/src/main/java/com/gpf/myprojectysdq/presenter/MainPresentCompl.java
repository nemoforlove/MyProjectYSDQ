package com.gpf.myprojectysdq.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.base.BasePager;
import com.gpf.myprojectysdq.view.activity.GameActivity;
import com.gpf.myprojectysdq.view.activity.HistoryActivity;
import com.gpf.myprojectysdq.view.activity.MainActivity;
import com.gpf.myprojectysdq.view.activity.SearchActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class MainPresentCompl implements IMainPresenter {

    private Intent intent;
    private MainActivity mainActivity;

    public MainPresentCompl(MainActivity context, Intent intent) {
        this.intent = intent;
        this.mainActivity = context;
    }

    @Override
    public void forwardSearch() {
        intent.setClass(mainActivity, SearchActivity.class);
        mainActivity.startActivity(intent);
    }

    @Override
    public void forwardGame() {
        intent.setClass(mainActivity, GameActivity.class);
        mainActivity.startActivity(intent);
    }

    @Override
    public void forwardHistory() {
        intent.setClass(mainActivity, HistoryActivity.class);
        mainActivity.startActivity(intent);
    }

    @Override
    public void setIntoFragment(final List<BasePager> list, final int position) {
        // 1.得到FragmentManager
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        // 2.创建事务
        FragmentTransaction ft = fragmentManager.beginTransaction();
        // 3.添加进fragment
        ft.replace(R.id.flContent,new Fragment(){
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                BasePager pager = getPager(list,position);
                if(pager != null){
                    // 返回各个页面的视图
                    return pager.rootView;
                }
                return null;
            }
        });
        // 4.提交事务
        ft.commit();
    }

    // 根据位置得到对应的页面
    @Override
    public BasePager getPager(List<BasePager> list,int position) {
        BasePager pager = list.get(position);
        if(pager != null && !pager.initData){
           pager.initData();// 连网请求或者绑定数据
            pager.initData = true;
        }
        return pager;
    }
}

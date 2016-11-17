package com.gpf.myprojectysdq.presenter;


import com.gpf.myprojectysdq.base.BasePager;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public interface IMainPresenter {
    void forwardSearch();// 主界面搜索

    void forwardGame();// 主界面游戏

    void forwardHistory();// 主界面历史记录

    // 将主界面下面的pager设置进fragment中
    void setIntoFragment(List<BasePager> list, int position);

    BasePager getPager(List<BasePager> list, int position);// 根据位置得到相应的页面
}

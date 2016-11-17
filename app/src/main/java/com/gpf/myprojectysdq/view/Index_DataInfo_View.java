package com.gpf.myprojectysdq.view;


import com.gpf.myprojectysdq.bean.Index_DataInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public interface Index_DataInfo_View {
    void showProgress();// 显示加载进度
    void addIndexDataInfo(List<Index_DataInfo> list);// 将获取到的数据填充给RecycleView
    void hideProgress();//隐藏加载进度
    void showLoadFailMsg();//加载失败时提示信息

}

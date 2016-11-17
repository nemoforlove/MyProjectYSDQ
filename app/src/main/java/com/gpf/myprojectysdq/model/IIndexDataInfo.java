package com.gpf.myprojectysdq.model;


import com.gpf.myprojectysdq.bean.Index_DataInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public interface IIndexDataInfo {
    Index_DataInfo getDataInfo();// 获取首页的整体对象
    List<Index_DataInfo.InfoBean.FocusBean> getFocusDataFromServer();// 首页图片轮播
    List<Index_DataInfo.InfoBean.BannerBean> getBannerDataFromServer();// 首页轮播下选项
}

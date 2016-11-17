package com.gpf.myprojectysdq.presenter;

import android.content.Context;


import com.gpf.myprojectysdq.bean.Index_DataInfo;
import com.gpf.myprojectysdq.model.IIndexDataInfo;
import com.gpf.myprojectysdq.model.IndexDataInfoCompl;
import com.gpf.myprojectysdq.view.Index_DataInfo_View;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class Index_DataInfo_PresenterCompl implements Index_DataInfo_Presenter {

    private IIndexDataInfo index_dataInfo;
    private Index_DataInfo_View index_dataInfo_view;

    public Index_DataInfo_PresenterCompl(Context context){
        index_dataInfo = new IndexDataInfoCompl(context);
    }

    @Override
    public Index_DataInfo getDataInfo() {
        return index_dataInfo.getDataInfo();
    }

    @Override
    public List<Index_DataInfo.InfoBean.FocusBean> getFocusDataFromServer() {
        return index_dataInfo.getFocusDataFromServer();
    }

    @Override
    public List<Index_DataInfo.InfoBean.BannerBean> getBannerDataFromServer() {
        return index_dataInfo.getBannerDataFromServer();
    }
}

package com.gpf.myprojectysdq.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gpf.myprojectysdq.bean.Index_DataInfo;
import com.gpf.myprojectysdq.constant.GlobalURL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class IndexDataInfoCompl implements IIndexDataInfo {
    private Context context;
    private Index_DataInfo index_dataInfo;

    public IndexDataInfoCompl(Context context){
        this.context = context;
    }

    @Override
    public Index_DataInfo getDataInfo() {
        RequestParams params = new RequestParams(GlobalURL.INDEX_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("test", result);
                // 使用Gson解析获得DataInfo对象
                Gson gson = new Gson();
                index_dataInfo = gson.fromJson(result, Index_DataInfo.class);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context, "数据加载失败！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return index_dataInfo;
    }

    @Override
    public List<Index_DataInfo.InfoBean.FocusBean> getFocusDataFromServer() {
        return getDataInfo().getInfo().getFocus();
    }

    @Override
    public List<Index_DataInfo.InfoBean.BannerBean> getBannerDataFromServer() {
        return getDataInfo().getInfo().getBanner();
    }
}

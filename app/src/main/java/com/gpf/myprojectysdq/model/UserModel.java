package com.gpf.myprojectysdq.model;

import android.util.Log;

import com.gpf.myprojectysdq.constant.GlobalURL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/12.
 */
public class UserModel implements IUser {

    private String data;

    // 查询登录数据 --- post请求
    @Override
    public void getFromServer(String name, String pass) {
        RequestParams params = new RequestParams(GlobalURL.MY_SERVER_LOGIN_URL);
        params.addBodyParameter("username",name);
        params.addBodyParameter("password",pass);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                Log.d("test","------------"+result);
                data = result;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}

package com.gpf.myprojectysdq.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.adapter.LiuYanAdapter;
import com.gpf.myprojectysdq.base.BaseActivity;
import com.gpf.myprojectysdq.bean.LiuYan;
import com.gpf.myprojectysdq.constant.GlobalURL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class LiuyanbanActivity extends BaseActivity {

    @ViewInject(R.id.lvLiuYan)
    private RecyclerView rvLiuYan;

    @Override
    public int setLayout() {
        return R.layout.activity_liuyanban;
    }

    @Override
    public void initView() {
       x.view().inject(this);
    }

    @Override
    public void getMethod() {

    }

    @Override
    public void initData() {
        RequestParams params = new RequestParams(GlobalURL.MY_SERVER_LIUYANBAN_URL);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                Toast.makeText(LiuyanbanActivity.this, "6666666"+result, Toast.LENGTH_SHORT).show();
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

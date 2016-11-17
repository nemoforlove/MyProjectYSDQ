package com.gpf.myprojectysdq.view.activity;

import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.base.BaseActivity;
import com.gpf.myprojectysdq.presenter.WelcomePresenter;

public class WelcomeActivity extends BaseActivity {

    private WelcomePresenter welcomePresenter;
    private Handler mHandler;

    @Override
    public int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void setStyle() {
        // 去掉状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
    }

    @Override
    public void initView() {
        mHandler = new Handler();
        welcomePresenter = new WelcomePresenter(this);
    }

    @Override
    public void getMethod() {
        welcomePresenter.start(mHandler);// 执行延迟跳转
    }

    // 当activity销毁时删除mHandler
    @Override
    protected void onDestroy() {
        welcomePresenter = null;
        mHandler.removeCallbacksAndMessages(null);// 移除handler
        super.onDestroy();
    }
}

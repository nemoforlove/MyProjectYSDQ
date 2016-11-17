package com.gpf.myprojectysdq.presenter;

import android.content.Intent;
import android.os.Handler;

import com.gpf.myprojectysdq.view.activity.MainActivity;
import com.gpf.myprojectysdq.view.activity.WelcomeActivity;

/**
 * Created by Administrator on 2016/11/11.
 */
public class WelcomePresenter {

    private final WelcomeActivity welcomeActivity;

    public WelcomePresenter(WelcomeActivity context) {
        this.welcomeActivity = context;
    }

    // 欢迎页面跳转至主界面
    public void start(Handler handler) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                welcomeActivity.startActivity(new Intent(welcomeActivity, MainActivity.class));
                welcomeActivity.finish();
            }
        }, 3000);
    }
}

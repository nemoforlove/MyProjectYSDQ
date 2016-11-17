package com.gpf.myprojectysdq.presenter;

/**
 * Created by Administrator on 2016/11/12.
 */
public interface ILogin {
    void clear();//清空文本框
    void doLogin(String name,String pass);//执行登录
    void doRegister(String name,String pass);//执行注册
    void showProgressBar(int visibility);// 是否显示进度
    void setEnable(boolean flag);
    // 新浪授权登录
    void loginSina();
    // QQ授权登录
    void loginQQ();
    // 微信授权登录
    void loginWeiXin();
}

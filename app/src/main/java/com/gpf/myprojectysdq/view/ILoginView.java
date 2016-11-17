package com.gpf.myprojectysdq.view;

/**
 * Created by Administrator on 2016/11/12.
 */
public interface ILoginView {
    void clear();// 清空文本框的方法
    int checkInfo(String username,String pass);// 检查用户输入的合法性
    void showProgress(int visibility);// 是否显示登录进度
    void setEnable(boolean flag);
}

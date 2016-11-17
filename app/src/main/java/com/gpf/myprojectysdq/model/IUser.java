package com.gpf.myprojectysdq.model;

/**
 * Created by Administrator on 2016/11/12.
 */
public interface IUser {
    // 从服务器查询该用户是否存在
    void getFromServer(String name,String pass);
    // 注册新用户

}

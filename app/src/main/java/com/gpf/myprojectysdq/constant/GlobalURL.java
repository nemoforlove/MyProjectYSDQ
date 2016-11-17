package com.gpf.myprojectysdq.constant;

/**
 * Created by Administrator on 2016/11/9.
 */

public interface GlobalURL {
    String MY_SERVER_LOGIN_URL = "http://10.0.2.2:8080/MyServer_YSDQ/LoginServlet";
    String MY_SERVER_REGISTER_URL = "http://10.0.2.2:8080/MyServer_YSDQ/RegisterServlet";
    String MY_SERVER_LIUYANBAN_URL = "http://10.0.2.2:8080/MyServer_YSDQ/QueryAllLiuYanServlet";
    String INDEX_URL = "http://api.2345shipin.com/?ctl=index&channel=sc-wandoujiafz_fr_lzc&api_ver=v4.6&vcode=5.0.2&sign=UlUIU1IFAlRTUVYACFICUwcBVAYBUVYBXgACBlFXXQY=";
    String CHANNEL_URL = "http://api.2345shipin.com/api.php?ctl=channel&api_ver=v4.6&vcode=5.0.2&sign=XF8AUVVSB1MHUlMACQ5SUwQPVAdRVANTVQBSXFRaXQo=";
    String LIUYANBAN_URL = "http://feedback.2345.com/feedback/list";
    String CHANNEL_MOVIE_URL = "http://api.2345shipin.com/api.php?ctl=channel&act=getZtData&zt=dy_zainan&channel=dy&page=1&from=app&api_ver=v4.2&vcode=5.0.2&sign=BVBTBAhdBwQFAgJTAwdSBlFdUQBSBwMCUVBQA1YGBwo=";
}

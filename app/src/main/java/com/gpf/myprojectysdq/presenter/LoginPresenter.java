package com.gpf.myprojectysdq.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gpf.myprojectysdq.app.App;
import com.gpf.myprojectysdq.bean.User;
import com.gpf.myprojectysdq.constant.GlobalURL;
import com.gpf.myprojectysdq.model.IUser;
import com.gpf.myprojectysdq.model.UserModel;
import com.gpf.myprojectysdq.view.ILoginView;
import com.gpf.myprojectysdq.view.activity.LoginActivity;
import com.gpf.myprojectysdq.view.activity.MainActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/12.
 */
public class LoginPresenter implements ILogin {

    ILoginView loginView;// V层引用
    IUser user;// m层引用
    LoginActivity context;

    public LoginPresenter(ILoginView iLoginView) {
        this.loginView = iLoginView;
        context = (LoginActivity) iLoginView;
        user = new UserModel();
    }

    @Override
    public void clear() {
        // 调用v层清空的方法
        loginView.clear();
    }

    @Override
    public void doLogin(final String name,String pass) {
        // 首先调用V层方法判断是否为空
        int i = loginView.checkInfo(name, pass);
        if(i == 0){
            // 输入不完整
            Toast.makeText(context, "用户名或者密码不能为空!", Toast.LENGTH_SHORT).show();
        }else{
            // 输入合法,则去请求服务器端
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
//                    Log.d("test","------------"+result);
                    if("fail".equals(result)){
                        Toast.makeText(context, "登录失败,用户名或者密码错误!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "恭喜,登录成功!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,MainActivity.class);
                        intent.putExtra("name",name);
                        context.startActivity(intent);
                    }
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
            loginView.clear();
        }
        loginView.showProgress(View.INVISIBLE);
        loginView.setEnable(true);
    }

    @Override
    public void doRegister(String name, String pass) {
        // 首先调用V层方法判断是否为空
        int i = loginView.checkInfo(name, pass);
        if(i == 0){
            // 输入不完整
            Toast.makeText(context, "用户名或者密码不能为空!", Toast.LENGTH_SHORT).show();
        }else{
            // 输入合法,则去请求服务器端
            RequestParams params = new RequestParams(GlobalURL.MY_SERVER_REGISTER_URL);
            params.addBodyParameter("username",name);
            params.addBodyParameter("password",pass);
            x.http().post(params, new Callback.CacheCallback<String>() {
                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
//                    Log.d("test","------------"+result);
                    Toast.makeText(context , result, Toast.LENGTH_SHORT).show();
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
            loginView.clear();
        }
        loginView.showProgress(View.INVISIBLE);
        loginView.setEnable(true);
    }

    @Override
    public void showProgressBar(int visibility) {
        loginView.showProgress(visibility);
    }

    @Override
    public void setEnable(boolean flag) {
        loginView.setEnable(flag);
    }

    @Override
    public void loginSina() {
        // 授权
        UMShareAPI  mShareAPI = UMShareAPI.get(context);
        mShareAPI.doOauthVerify(context, SHARE_MEDIA.SINA, umAuthListener);
        UMShareAPI umShareAPI = ((App) context.getApplicationContext()).getUmShareAPI();
        umShareAPI.getPlatformInfo(context, SHARE_MEDIA.SINA, umAuthListener);
    }

    @Override
    public void loginQQ() {
        // 授权
        UMShareAPI  mShareAPI = UMShareAPI.get(context);
        mShareAPI.doOauthVerify(context, SHARE_MEDIA.QQ, umAuthListener);
        UMShareAPI umShareAPI = ((App) context.getApplicationContext()).getUmShareAPI();
        umShareAPI.getPlatformInfo(context, SHARE_MEDIA.QQ, umAuthListener);
    }

    @Override
    public void loginWeiXin() {
        Toast.makeText(context, "系统维护,请先使用其他第三方账号登录!", Toast.LENGTH_SHORT).show();
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //遍历Map
            Iterator ite =data.entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry entry = (Map.Entry)ite.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                Log.d("test",key+"---->"+value);
                if("screen_name".equals(entry.getKey())){
                    Toast.makeText(context, "恭喜:"+entry.getValue()+",登陆成功!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( context, "登录失败!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
        }
    };
}

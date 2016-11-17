package com.gpf.myprojectysdq.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.base.BaseActivity;
import com.gpf.myprojectysdq.presenter.ILogin;
import com.gpf.myprojectysdq.presenter.LoginPresenter;
import com.gpf.myprojectysdq.view.ILoginView;
import com.umeng.socialize.UMShareAPI;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class LoginActivity extends BaseActivity implements ILoginView, OnClickListener {

    @ViewInject(R.id.username)
    private EditText etName;
    @ViewInject(R.id.password)
    private EditText etPass;
    @ViewInject(R.id.login)
    private Button login;
    @ViewInject(R.id.register)
    private Button register;
    @ViewInject(R.id.btnSina)
    private ImageButton btnSina;
    @ViewInject(R.id.btnQQ)
    private ImageButton btnQQ;
    @ViewInject(R.id.btnWeiXin)
    private ImageButton btnWeiXin;
    @ViewInject(R.id.loginPro)
    private ProgressBar loginPro;
    private ILogin loginPresenter;

    @Override
    public void setStyle() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        x.view().inject(this);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void setListener() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        btnSina.setOnClickListener(this);
        btnQQ.setOnClickListener(this);
        btnWeiXin.setOnClickListener(this);
    }

    @Override
    public void clear() {
        etName.setText("");
        etPass.setText("");
    }

    @Override
    public int checkInfo(String username, String pass) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pass)) {
            return 1;
        }
        return 0;
    }

    @Override
    public void showProgress(int visibility) {
        loginPro.setVisibility(visibility);
    }

    @Override
    public void setEnable(boolean flag) {
            login.setEnabled(flag);
            register.setEnabled(flag);
            btnQQ.setEnabled(flag);
            btnSina.setEnabled(flag);
            btnWeiXin.setEnabled(flag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                // 显示登录进度
                loginPresenter.showProgressBar(View.VISIBLE);
                // 设置按钮不可用
                loginPresenter.setEnable(false);
                // 执行登录
                loginPresenter.doLogin(etName.getText().toString(), etPass.getText().toString());
                break;
            case R.id.register:
//                Toast.makeText(LoginActivity.this, "=======", Toast.LENGTH_SHORT).show();
                // 调用服务器端注册的方法
                // 显示注册进度
                loginPresenter.showProgressBar(View.VISIBLE);
                // 设置按钮不可用
                loginPresenter.setEnable(false);
                // 执行注册
                loginPresenter.doRegister(etName.getText().toString(), etPass.getText().toString());
                break;
            case R.id.btnSina:
                loginPresenter.loginSina();
                break;
            case R.id.btnQQ:
                loginPresenter.loginQQ();
                break;
            case R.id.btnWeiXin:
                loginPresenter.loginWeiXin();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}

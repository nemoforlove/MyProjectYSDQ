package com.gpf.myprojectysdq.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.base.BaseActivity;
import com.gpf.myprojectysdq.bean.SearchWebData;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class SearchGoActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.searchGoBack)
    private ImageView searchGoBack;
    @ViewInject(R.id.searchGoEdit)
    private EditText searchGOEdit;
    @ViewInject(R.id.searchGoSearch)
    private ImageView searchGoSearch;
    @ViewInject(R.id.searchGoClear)
    private ImageView searchGoClear;
    @ViewInject(R.id.searchGoWeb1)
    private WebView webView1;
    @ViewInject(R.id.searchGoBar)
    private ProgressBar searchGoBar;

    private List<SearchWebData> list = new ArrayList<>();
    String key = null;
//    private Progress dialog = new ProgressDialog(this);


    @Override
    public int setLayout() {
        return R.layout.activity_search_go;
    }

    @Override
    public void initView() {
        x.view().inject(this);
        Intent intent = getIntent();
        searchGoBar = new ProgressBar(this);
        //去掉跳进网页播放
        WebSettings settings = webView1.getSettings();
        settings.setJavaScriptEnabled(true);
        key = intent.getStringExtra("key");
        searchGOEdit.setText(key);
//        List<SearchData> request1 = JuHeSearch.getRequest1(key);
//        new JuHeSearch(this);
        String url ="http://op.juhe.cn/onebox/movie/video";//请求接口地址
//        final Map params = new HashMap();//请求参数
//        params.put("key","97db86787e9127a16f2c9c06fc1babd8");//应用APPKEY(应用详细页查询)
//        params.put("dtype","json");//返回数据的格式,xml或json，默认json
//        params.put("q",Str);//影视搜索名称
        RequestParams params= new RequestParams(url);
        params.addBodyParameter("key","97db86787e9127a16f2c9c06fc1babd8");
        params.addBodyParameter("dtype","json");
        params.addBodyParameter("q",key);
        x.http().post(params, new Callback.ProgressCallback<String>() {


            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
//                Toast.makeText(SearchGoActivity.this, "开始获取数据。。", Toast.LENGTH_SHORT).show();

                searchGoBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
//                Toast.makeText(SearchGoActivity.this, "正在获取数据。。", Toast.LENGTH_SHORT).show();
                searchGoBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onSuccess(String result) {
//                txt.setText(result);
                try {
                    JSONObject object = new JSONObject(result);
                    JSONObject ob1 = object.getJSONObject("result");
                    JSONObject play = ob1.getJSONObject("playlinks");
                    String qq = play.getString("qq");
                    String qiyi = play.getString("qiyi");
                    String baofeng = play.getString("baofeng");
                    String pptv = play.getString("pptv");
                    list.add(new SearchWebData(qq,qiyi,baofeng,pptv));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                webView1.loadUrl(list.get(0).getPptv());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(SearchGoActivity.this, "网络已经断开，请重新连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
//                Toast.makeText(SearchGoActivity.this, "获取数据完成。。", Toast.LENGTH_SHORT).show();
                searchGoBar.setVisibility(View.GONE);

            }

        });
    }

    @Override
    public void getMethod() {

    }

    @Override
    public void setStyle() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
    }

    @Override
    public void setListener() {
        searchGoBack.setOnClickListener(this);
        searchGoSearch.setOnClickListener(this);
        searchGoClear.setOnClickListener(this);

        webView1.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.searchGoBack:
                Intent intent2 = new Intent(SearchGoActivity.this,SearchActivity.class);
//                intent2.putExtra("reKey",key);
                startActivity(intent2);
                finish();
                break;
            case R.id.searchGoSearch:
                break;
            case R.id.searchGoClear:
                Intent intent1 = new Intent(SearchGoActivity.this,SearchActivity.class);
                startActivity(intent1);
                break;
        }

    }
}

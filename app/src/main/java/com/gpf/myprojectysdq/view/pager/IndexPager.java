package com.gpf.myprojectysdq.view.pager;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.adapter.MyIndexAdapter;
import com.gpf.myprojectysdq.base.BasePager;
import com.gpf.myprojectysdq.bean.Index_DataInfo;
import com.gpf.myprojectysdq.bean.Index_DataInfo.InfoBean.BannerBean;
import com.gpf.myprojectysdq.bean.Index_DataInfo.InfoBean.FocusBean;
import com.gpf.myprojectysdq.bean.Index_DataInfo.InfoBean.RecommendBean;
import com.gpf.myprojectysdq.constant.GlobalURL;
import com.gpf.myprojectysdq.utils.PrefUtils;
import com.gpf.myprojectysdq.view.Index_DataInfo_View;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import static com.gpf.myprojectysdq.bean.Index_DataInfo.*;

/**
 * 首页页面
 */

public class IndexPager extends BasePager implements Index_DataInfo_View {

    @ViewInject(R.id.slRefresh)
    private SwipeRefreshLayout slRefresh;
    @ViewInject(R.id.rvIndexInfo)
    private ListView rvIndexInfo;
    @ViewInject(R.id.pgLoad)
    private ProgressBar progressBar;
    @ViewInject(R.id.toTop)
    private FloatingActionButton toTop;

    private List<RecommendBean> recommend;
    private List<FocusBean> focus;
    private List<BannerBean> banner;

    public IndexPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.main_index_pager_view, null);
        x.view().inject(this, view);
//        slRefresh.setColorSchemeResources(android.R.color.holo_blue_light,android.R.color.holo_red_light,
//                android.R.color.holo_orange_light,android.R.color.holo_green_light);
        slRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新
//                tvRefresh.setText("正在刷新");
                // 重新获取数据
                initData();
                // 刷新完成
//                tvRefresh.setText("刷新完成");
                slRefresh.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        String cache = PrefUtils.getString(context, GlobalURL.INDEX_URL, "");
        if(!TextUtils.isEmpty(cache)){
            parseData(cache);
            progressBar.setVisibility(View.GONE);
        }else{
            // 执行网络请求
            RequestParams params = new RequestParams(GlobalURL.INDEX_URL);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    // 将数据保存在缓存文件中
                    PrefUtils.setString(context,GlobalURL.INDEX_URL,result);
                    parseData(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Toast.makeText(context, "数据获取失败！", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        // 悬浮按钮监听
        toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvIndexInfo.setSelection(0);
            }
        });
    }

    private void parseData(String result) {
        Gson gson = new Gson();
        Index_DataInfo dataInfo = gson.fromJson(result, Index_DataInfo.class);
        focus = dataInfo.getInfo().getFocus();
        banner = dataInfo.getInfo().getBanner();
        recommend = dataInfo.getInfo().getRecommend();
//                Log.d("test","9999999999===="+recommend.size());
        // 设置适配器
        MyIndexAdapter adapter = new MyIndexAdapter(context, recommend, focus, banner);
        rvIndexInfo.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        slRefresh.setRefreshing(true);
    }

    @Override
    public void addIndexDataInfo(List<Index_DataInfo> list) {

    }

    @Override
    public void hideProgress() {
        slRefresh.setRefreshing(true);
    }

    @Override
    public void showLoadFailMsg() {
        Snackbar.make(this.rootView, "数据加载失败，请检查网络设置！", Snackbar.LENGTH_SHORT).show();
    }
}

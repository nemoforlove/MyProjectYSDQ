package com.gpf.myprojectysdq.view.pager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.adapter.HistoryAdapter;
import com.gpf.myprojectysdq.app.App;
import com.gpf.myprojectysdq.base.BasePager;
import com.gpf.myprojectysdq.bean.Video;
import com.gpf.myprojectysdq.view.activity.LocalActivity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页页面
 */

public class HistoryPager extends BasePager {

    @ViewInject(R.id.RlHistory)
    private RecyclerView rlHistory;
    @ViewInject(R.id.tvNoData)
    private TextView tvNoData;
    private DbManager db;

    public HistoryPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View v = LayoutInflater.from(context).inflate(R.layout.history_pager_layout, null);
        x.view().inject(this, v);
        return v;
    }

    @Override
    public void initData() {
        // 从数据库获取数据，
        db = x.getDb(((App) context.getApplicationContext()).getDaoConfig());
        try {
            List<Video> list = db.findAll(Video.class);
            // 设置适配器
            if (list != null) {
                HistoryAdapter adapter = new HistoryAdapter(context, list);
                rlHistory.setLayoutManager(new LinearLayoutManager(context));
                rlHistory.setAdapter(adapter);
            }else{
                tvNoData.setVisibility(View.VISIBLE);// 设置可见
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}

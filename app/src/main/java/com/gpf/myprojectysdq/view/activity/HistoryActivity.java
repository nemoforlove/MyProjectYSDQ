package com.gpf.myprojectysdq.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.adapter.HistoryAndCollectionAdapter;
import com.gpf.myprojectysdq.adapter.MyLocalVideoAdapter;
import com.gpf.myprojectysdq.app.App;
import com.gpf.myprojectysdq.base.BaseActivity;
import com.gpf.myprojectysdq.base.BasePager;
import com.gpf.myprojectysdq.bean.Video;
import com.gpf.myprojectysdq.view.pager.CollectionPager;
import com.gpf.myprojectysdq.view.pager.HistoryPager;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends BaseActivity {

    @ViewInject(R.id.tlRecordAndHistory)
    private TabLayout tlRecordAndHistory;
    @ViewInject(R.id.vpRecordAndHistory)
    private ViewPager vpRecordAndHistory;
    private List<BasePager> list;

    @Override
    public int setLayout() {
        return R.layout.activity_history;
    }

    @Override
    public void initView() {
        x.view().inject(this);
    }


    @Override
    public void initData() {
        //
        list = new ArrayList<>();
        list.add(new HistoryPager(this));
        list.add(new CollectionPager(this));
        // 设置适配器
        HistoryAndCollectionAdapter adapter = new HistoryAndCollectionAdapter(this,list);
        vpRecordAndHistory.setAdapter(adapter);
        tlRecordAndHistory.setupWithViewPager(vpRecordAndHistory);
    }
}

package com.gpf.myprojectysdq.view.pager;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.adapter.LixianAdapter;
import com.gpf.myprojectysdq.base.BasePager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页页面
 */

public class DownloadPager extends BasePager {

    @ViewInject(R.id.tyTitle)
    private TabLayout tyTitle;
    @ViewInject(R.id.vpContent)
    private ViewPager vpContent;
    @ViewInject(R.id.tvProgress)
    private TextView tvProgress;
    @ViewInject(R.id.tvText)
    private TextView tvText;
    private List<BasePager> list;

    public DownloadPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.main_download_pager_view,null);
        x.view().inject(this,view);
        return view;
    }

    private void setAdapter() {
        LixianAdapter adapter = new LixianAdapter(context,list);
        vpContent.setAdapter(adapter);
        tyTitle.setupWithViewPager(vpContent);
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        list.add(new LixianIngPager(context));
        list.add(new LixianDonePager(context));
        setAdapter();
    }


}

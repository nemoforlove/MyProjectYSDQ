package com.gpf.myprojectysdq.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.gpf.myprojectysdq.base.BasePager;
import com.gpf.myprojectysdq.view.activity.HistoryActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public class HistoryAndCollectionAdapter extends PagerAdapter {

    private final HistoryActivity historyActivity;
    private final List<BasePager> list;
    private String[] titles = new String[]{"播放历史","我的收藏"};

    public HistoryAndCollectionAdapter(HistoryActivity historyActivity, List<BasePager> list) {
        this.historyActivity = historyActivity;
        this.list = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager basePager = list.get(position);
        container.addView(basePager.rootView);
        return basePager.rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

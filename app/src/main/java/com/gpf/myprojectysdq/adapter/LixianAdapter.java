package com.gpf.myprojectysdq.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.gpf.myprojectysdq.base.BasePager;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class LixianAdapter extends PagerAdapter {

    private final Context context;
    private final List<BasePager> list;
    private String[] titles = new String[]{"离线中","已离线"};

    public LixianAdapter(Context context, List<BasePager> list) {
        this.context = context;
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

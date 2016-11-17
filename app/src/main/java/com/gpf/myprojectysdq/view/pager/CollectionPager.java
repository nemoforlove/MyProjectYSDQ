package com.gpf.myprojectysdq.view.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.base.BasePager;

import org.xutils.x;

/**
 * 首页页面
 */

public class CollectionPager extends BasePager {


    public CollectionPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View v = LayoutInflater.from(context).inflate(R.layout.collection_pager_layout,null);
        x.view().inject(this,v);
        return v;
    }

    @Override
    public void initData() {
        
    }
}

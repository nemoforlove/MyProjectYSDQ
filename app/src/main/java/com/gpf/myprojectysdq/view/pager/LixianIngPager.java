package com.gpf.myprojectysdq.view.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.base.BasePager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/10.
 */

public class LixianIngPager extends BasePager {

    @ViewInject(R.id.lvLixianIng)
    private ListView lvLixian;
    @ViewInject(R.id.llLixian)
    private LinearLayout ll;

    public LixianIngPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.lixianing,null);
        x.view().inject(this,view);
        return view;
    }
    @Override
    public void initData() {
        lvLixian.setEmptyView(ll);
    }
}

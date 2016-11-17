package com.gpf.myprojectysdq.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.bean.Index_DataInfo;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class IndexViewPager extends PagerAdapter {
    private Context context;
    private List<Index_DataInfo.InfoBean.FocusBean> list;

    public IndexViewPager(Context context, List<Index_DataInfo.InfoBean.FocusBean> focus) {
        this.context = context;
        this.list = focus;
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
        Index_DataInfo.InfoBean.FocusBean focusBean = list.get(position);
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(iv);
        ImageOptions options = new ImageOptions.Builder()
                .setUseMemCache(true)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setLoadingDrawableId(R.mipmap.ly_default_banner)
                .build();
        x.image().bind(iv,focusBean.getPic(),options);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

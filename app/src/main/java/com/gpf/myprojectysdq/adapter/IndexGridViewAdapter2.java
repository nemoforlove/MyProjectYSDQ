package com.gpf.myprojectysdq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.bean.Index_DataInfo;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class IndexGridViewAdapter2 extends BaseAdapter {

    private final Context context;
    private final List<Index_DataInfo.InfoBean.RecommendBean.ListBean> list;

    public IndexGridViewAdapter2(Context context, List<Index_DataInfo.InfoBean.RecommendBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.index_list_grid_item2,null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView tvTitle1 = (TextView) convertView.findViewById(R.id.tvTitle1);
        Index_DataInfo.InfoBean.RecommendBean.ListBean listBean = list.get(position);
        tvTitle1.setText(listBean.getTitle());
        x.image().bind(imageView,listBean.getPic());
        return convertView;
    }
}

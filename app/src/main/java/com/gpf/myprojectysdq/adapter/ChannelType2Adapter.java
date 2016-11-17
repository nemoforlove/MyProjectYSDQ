package com.gpf.myprojectysdq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.bean.ChannelInfo;
import com.gpf.myprojectysdq.bean.ChannelInfo.InfoBean.CustomBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class ChannelType2Adapter extends BaseAdapter {

    private final Context context;
    private final List<CustomBean> custom;

    public ChannelType2Adapter(Context context, List<CustomBean> custom) {

        this.context = context;
        this.custom = custom;
    }

    @Override
    public int getCount() {
        return custom.size();
    }

    @Override
    public Object getItem(int position) {
        return custom.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.channel_type2,null);
        ImageView iv = (ImageView) convertView.findViewById(R.id.ivIcon);
        TextView tv = (TextView) convertView.findViewById(R.id.tvType);
        CustomBean customBean = custom.get(position);
        tv.setText(customBean.getName());
        x.image().bind(iv,customBean.getIcon());
        return convertView;
    }
}

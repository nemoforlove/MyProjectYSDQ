package com.gpf.myprojectysdq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.bean.ChannelInfo;
import com.gpf.myprojectysdq.bean.ChannelInfo.InfoBean.ChannelBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class ChannelType1Adapter extends BaseAdapter {

    private final Context context;
    private final List<ChannelBean> channel;

    public ChannelType1Adapter(Context context, List<ChannelBean> channel) {

        this.context = context;
        this.channel = channel;
    }

    @Override
    public int getCount() {
        return channel.size();
    }

    @Override
    public Object getItem(int position) {
        return channel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case 0:
                convertView = LayoutInflater.from(context).inflate(R.layout.channel_type1_item01, null);
                break;
            case 1:
                convertView = LayoutInflater.from(context).inflate(R.layout.channel_type1_item02, null);
                break;
            case 2:
                convertView = LayoutInflater.from(context).inflate(R.layout.channel_type1_item03, null);
                break;
            case 3:
                convertView = LayoutInflater.from(context).inflate(R.layout.channel_type1_item04, null);
                break;
        }
        ImageView iv = (ImageView) convertView.findViewById(R.id.ivImage);
        ChannelBean channelBean = channel.get(position);
        x.image().bind(iv,channelBean.getIcon());
        return convertView;
    }
}

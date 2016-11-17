package com.gpf.myprojectysdq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.bean.Index_DataInfo;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class GridViewAdapter extends BaseAdapter{
    private final Context context;
    private final List<Index_DataInfo.InfoBean.BannerBean> list;

    public GridViewAdapter(Context context, List<Index_DataInfo.InfoBean.BannerBean> banner) {

        this.context = context;
        this.list = banner;
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
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item0_grid_item,null);
            holder.ivType = (ImageView) convertView.findViewById(R.id.ivType);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Index_DataInfo.InfoBean.BannerBean bannerBean = list.get(position);
        holder.tvName.setText(bannerBean.getName());
        Glide.with(context).load(bannerBean.getIcon()).error(R.mipmap.ic_launcher).into(holder.ivType);
//        x.image().bind(holder.ivType,bannerBean.getIcon());
        return convertView;
    }

    static class ViewHolder{
        ImageView ivType;
        TextView tvName;
    }
}

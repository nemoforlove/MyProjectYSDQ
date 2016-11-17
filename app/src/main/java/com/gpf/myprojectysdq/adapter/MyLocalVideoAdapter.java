package com.gpf.myprojectysdq.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.bean.Video;
import com.gpf.myprojectysdq.utils.TimeUtils;
import com.gpf.myprojectysdq.view.activity.LocalActivity;

import java.util.Formatter;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12.
 */
public class MyLocalVideoAdapter extends BaseAdapter {

    private final Context localActivity;
    private final List<Video> list;

    public MyLocalVideoAdapter(Context localActivity, List<Video> list) {
        this.localActivity = localActivity;
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
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(localActivity).inflate(R.layout.local_video_item,null);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
            holder.tvSize = (TextView) convertView.findViewById(R.id.tvSize);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Video video = list.get(position);
        holder.tvName.setText(video.getName());
        holder.tvDuration.setText(TimeUtils.formatTime(video.getDuration()));
        holder.tvSize.setText(android.text.format.Formatter.formatFileSize(localActivity,video.getSize()));
        return convertView;
    }

    static class ViewHolder{
        TextView tvName,tvDuration,tvSize;
    }
}

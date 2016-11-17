package com.gpf.myprojectysdq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.bean.Video;
import com.gpf.myprojectysdq.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyHolder> {


    private final Context context;
    private final List<Video> list;
    private LayoutInflater inflater;

    public HistoryAdapter(Context context, List<Video> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.history_list_item,null);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Video video = list.get(position);
        holder.tvName.setText(video.getName());
        holder.tvDuration.setText(TimeUtils.formatTime(video.getDuration()));
        holder.tvSize.setText(android.text.format.Formatter.formatFileSize(context,video.getSize()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private TextView tvName; // 标题
        private TextView tvSize;// 大小
        private TextView tvDuration;// 时间

        public MyHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvSize = (TextView) itemView.findViewById(R.id.tvSize);
            tvDuration = (TextView) itemView.findViewById(R.id.tvDuration);
        }
    }
}

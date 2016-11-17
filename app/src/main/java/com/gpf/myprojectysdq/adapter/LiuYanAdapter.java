package com.gpf.myprojectysdq.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.bean.LiuYan;
import com.gpf.myprojectysdq.view.activity.LiuyanbanActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public class LiuYanAdapter extends RecyclerView.Adapter<LiuYanAdapter.MyViewHolder> {

    private final LiuyanbanActivity liuyanbanActivity;
    private final List<LiuYan> list;
    private LayoutInflater inflater;

    public LiuYanAdapter(LiuyanbanActivity liuyanbanActivity, List<LiuYan> result) {

        this.liuyanbanActivity = liuyanbanActivity;
        this.list = result;
        inflater = LayoutInflater.from(liuyanbanActivity);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.message_list,null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LiuYan liuYan = list.get(position);
        holder.topic_name.setText(liuYan.getTopic_name());
        holder.topic_content.setText(liuYan.getTopic_content());
        holder.topic_time.setText(liuYan.getTopic_time());
        holder.replay_content.setText(liuYan.getReplay_content());
        holder.replay_time.setText(liuYan.getReplay_time());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView topic_name,topic_content,topic_time,replay_content,replay_time;
        public MyViewHolder(View itemView) {
            super(itemView);
            topic_name = (TextView) itemView.findViewById(R.id.topic_name);
            topic_content = (TextView) itemView.findViewById(R.id.topic_content);
            topic_time = (TextView) itemView.findViewById(R.id.topic_time);
            replay_content = (TextView) itemView.findViewById(R.id.replay_content);
            replay_time = (TextView) itemView.findViewById(R.id.replay_time);
        }
    }
}

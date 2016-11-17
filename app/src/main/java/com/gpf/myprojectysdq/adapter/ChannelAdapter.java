package com.gpf.myprojectysdq.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.bean.ChannelInfo;
import com.gpf.myprojectysdq.bean.ChannelInfo.InfoBean.ChannelBean;
import com.gpf.myprojectysdq.bean.ChannelInfo.InfoBean.CustomBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public class ChannelAdapter extends BaseAdapter {

    private final Context context;
    private final List<ChannelBean> channel;
    private final List<CustomBean> custom;

    public ChannelAdapter(Context context, List<ChannelBean> channel, List<CustomBean> custom) {
        this.context = context;
        this.channel = channel;
        this.custom = custom;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        if(position == 0){
            return channel;
        }else if(position == 2){
            return custom;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position%3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            if(position == 0){
                convertView = LayoutInflater.from(context).inflate(R.layout.channel_type01,null);
                GridView gv1 = (GridView) convertView.findViewById(R.id.channel_type01);
                // 为gridView设置适配器
                ChannelType01Adapter adapter1 = new ChannelType01Adapter(context,channel);
                // 为gridView设置值监听
                gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, "点击了"+position, Toast.LENGTH_SHORT).show();
                    }
                });
                gv1.setAdapter(adapter1);
                return convertView;
            }
            if(position == 1){
                LinearLayout ll = new LinearLayout(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                TextView tv = new TextView(context);
                params.setMargins(8,8,0,5);
                tv.setTextSize(15);
                tv.setTextColor(Color.BLACK);
                tv.setLayoutParams(params);
                tv.setText("精选频道");
                ll.addView(tv);
                return ll;
            }
            if(position == 2){
                convertView = LayoutInflater.from(context).inflate(R.layout.channel_type02,null);
                GridView gv2 = (GridView) convertView.findViewById(R.id.channel_type02);
                // 为gridView设置适配器
                ChannelType02Adapter adapter2 = new ChannelType02Adapter(context,custom);
                // 为gridView设置值监听
                gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, "点击了"+position, Toast.LENGTH_SHORT).show();
                    }
                });
                gv2.setAdapter(adapter2);
                return convertView;
            }
        }
        return null;
    }
}

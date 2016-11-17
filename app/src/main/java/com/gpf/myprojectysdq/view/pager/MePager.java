package com.gpf.myprojectysdq.view.pager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.base.BasePager;
import com.gpf.myprojectysdq.view.activity.LocalActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页页面
 */

public class MePager extends BasePager {

    @ViewInject(R.id.LoginImage)
    private ImageView ivLogin;
    @ViewInject(R.id.lvMe)
    private ListView lvMe;
    private String[] types = new String[]{"我的离线","我的收藏","本地视频","留言板"};
    private int[] images = new int[]{R.mipmap.ys_own_ic_load,R.mipmap.ys_own_ic_collect,
            R.mipmap.ys_own_ic_local,R.mipmap.ys_own_ic_feedback,};

    public MePager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.main_me_pager_view,null);
        x.view().inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<types.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("type",types[i]);
            map.put("icon",images[i]);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(context,list,R.layout.main_me_list_item,
                new String[]{"icon","type"},new int[]{R.id.ivIcon,R.id.tvType});
        lvMe.setAdapter(adapter);
        lvMe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
//                        intent.setClass(context,)
                        break;
                    case 1:
                        break;
                    case 2:
                        intent.setClass(context,LocalActivity.class);
                        break;
                    case 3:
                        break;
                }
                context.startActivity(intent);
                Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.gpf.myprojectysdq.view.pager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.adapter.MyLocalVideoAdapter;
import com.gpf.myprojectysdq.app.App;
import com.gpf.myprojectysdq.base.BasePager;
import com.gpf.myprojectysdq.bean.Video;
import com.gpf.myprojectysdq.view.activity.LocalActivity;
import com.gpf.myprojectysdq.view.activity.PlayMovieActivity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/14.
 */
public class LocalMovies extends BasePager {

    @ViewInject(R.id.lvMovies)
    private ListView lvMovies;
    @ViewInject(R.id.tvInfo)
    private TextView tvShow;
    @ViewInject(R.id.pbLoading)
    private ProgressBar pbLoading;
    private ArrayList<Video> list;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (list != null && list.size() > 0) {
                // 说明有数据,则设置适配器，隐藏文本和进度条
                MyLocalVideoAdapter adapter = new MyLocalVideoAdapter(context, list);
                lvMovies.setAdapter(adapter);
                tvShow.setVisibility(View.GONE);
            } else {
                // 没有数据，显示文本，隐藏进度条
                tvShow.setVisibility(View.VISIBLE);
            }
            pbLoading.setVisibility(View.GONE);
        }
    };
    private DbManager db;

    public LocalMovies(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View v = LayoutInflater.from(context).inflate(R.layout.pager_local, null);
        x.view().inject(this, v);
        return v;
    }

    @Override
    public void initData() {
        // 获取本地的视频数据，
        getDataFromLocal();
        setListener();
    }

    private void setListener() {
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video = list.get(position);
//                Toast.makeText(context, video.toString(), Toast.LENGTH_SHORT).show();
                // 调用播放器 --- 传递单个地址
                /*Intent intent = new Intent(LocalActivity.this,PlayMovieActivity.class);
                intent.putExtra("path",video.getPath());
                startActivity(intent);*/
                // 传递整个集合过去 -- 前提：对象要实现序列化接口，然后借助于Bundle
                Intent intent = new Intent(context,PlayMovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",list);
                intent.putExtras(bundle);
                intent.putExtra("position",position);
                context.startActivity(intent);

                // 将本次记录保存在数据库
                db = x.getDb(((App)context.getApplicationContext()).getDaoConfig());
                try {
                    db.save(video);
                    Toast.makeText(context, "已保存记录！", Toast.LENGTH_SHORT).show();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 使用ContentResolver从本地的SD卡获得数据 --- 耗时操作
    private void getDataFromLocal() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                list = new ArrayList<>();
                ContentResolver resolver = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,// 名字
                        MediaStore.Video.Media.DURATION,// 时长
                        MediaStore.Video.Media.SIZE,// 大小
                        MediaStore.Video.Media.DATA// 绝对路径
                };
                Cursor cursor = resolver.query(uri, objs, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(0);
                        long duration = cursor.getLong(1);
                        long size = cursor.getLong(2);
                        String path = cursor.getString(3);
                        Video v = new Video(name, duration, size, path);
                        list.add(v);
                    }
                }

                // 发消息
                mHandler.sendEmptyMessage(0x11);
            }
        }.start();
    }
}

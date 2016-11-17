package com.gpf.myprojectysdq.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.bean.Index_DataInfo;
import com.gpf.myprojectysdq.bean.Index_DataInfo.InfoBean.RecommendBean;
import com.gpf.myprojectysdq.bean.Index_DataInfo.InfoBean.RecommendBean.ListBean;
import com.gpf.myprojectysdq.view.activity.IndexPlayActivity;
import com.gpf.myprojectysdq.view.activity.PlayMovieActivity;
import com.viewpagerindicator.CirclePageIndicator;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class MyIndexAdapter extends BaseAdapter {

    private final Context context;
    private final List<RecommendBean> list;
    private final LayoutInflater inflate;
    private List<Index_DataInfo.InfoBean.FocusBean> focus;
    private List<Index_DataInfo.InfoBean.BannerBean> banner;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0x11:
                    int currentItem = viewPager.getCurrentItem();
                    currentItem++;
                    if(currentItem >= focus.size()){
                        currentItem=0;
                    }
                    viewPager.setCurrentItem(currentItem);
                    mHandler.sendEmptyMessageDelayed(0X11,3000);
                    break;
            }
        }
    };
    private ViewPager viewPager;

    public MyIndexAdapter(Context context, List<RecommendBean> recommend, List<Index_DataInfo.InfoBean.FocusBean> focus, List<Index_DataInfo.InfoBean.BannerBean> banner) {
        this.context = context;
        this.list = recommend;
        this.focus = focus;
        this.banner = banner;
        inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return list.size()+1;
    }

    @Override
    public Object getItem(int position) {
        if(position > 0){
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 18;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 18;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Intent intent = new Intent(context, PlayMovieActivity.class);
        if (getItemViewType(position) == 0) {
            // 头部
            convertView = inflate.inflate(R.layout.index_list_item_head, parent, false);
            viewPager = (ViewPager) convertView.findViewById(R.id.vpIndexFocus);
            final TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            CirclePageIndicator indicator = (CirclePageIndicator) convertView.findViewById(R.id.indicator);
            GridView gvSelect = (GridView) convertView.findViewById(R.id.gvSelect);
            tvTitle.setText(focus.get(0).getTitle());
            // 为ViewPager设置适配器
            IndexViewPager adapter1 = new IndexViewPager(context,focus);
            GridViewAdapter adapter2 = new GridViewAdapter(context,banner);
            viewPager.setAdapter(adapter1);
            gvSelect.setAdapter(adapter2);
            indicator.setViewPager(viewPager);
            //
            mHandler.sendEmptyMessageDelayed(0x11,3000);
            indicator.setOnPageChangeListener(new OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                     tvTitle.setText(focus.get(position).getTitle());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }
        if (getItemViewType(position) == 1) {
            // 今日热门
            final RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                      String play_url = recommendBean.getList().get(position).getUrl();
//                      String title = recommendBean.getList().get(position).getTitle();
                    String path = "https://sinacloud.net/gpf.com.cn/01 百里登风.flv";
                    Uri uri = Uri.parse(path);
                    intent.setDataAndType(uri,"video/*");
                    context.startActivity(intent);
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 2) {
            // 院线大片
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item_yuanxian, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            ImageView ivLarge = (ImageView) convertView.findViewById(R.id.ivLarge);
            TextView tvTitle1 = (TextView) convertView.findViewById(R.id.tvTitle1);
            TextView tvTitle2 = (TextView) convertView.findViewById(R.id.tvTitle2);
            List<ListBean> list = recommendBean.getList();
            final ListBean listBean = list.get(0);
            Glide.with(context).load(listBean.getPic()).placeholder(R.mipmap.ly_default_banner)
                    .error(R.mipmap.ly_default_banner).into(ivLarge);
//            x.image().bind(ivLarge, listBean.getPic());
            ivLarge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* String play_url = listBean.getUrl();
                    intent.putExtra("url",play_url);*/
                    String path = "https://sinacloud.net/gpf.com.cn/bb.flv";
                    Uri uri = Uri.parse(path);
                    intent.setDataAndType(uri,"video/*");
                    context.startActivity(intent);
//                    Toast.makeText(context, "点击了大图！---"+listBean.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            tvTitle1.setText(listBean.getTitle());
            tvTitle2.setText(listBean.getDescription());
            textView.setText(recommendBean.getName());
            final List<ListBean> list2 = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                list2.add(list.get(i));
            }
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list2);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                    String play_url = list2.get(position).getUrl();
                    String title = list2.get(position).getTitle();
                    intent.putExtra("url",play_url);
//                    Toast.makeText(context, "8999988"+title, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 3) {
            // 复仇者
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item2, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }

        if (getItemViewType(position) == 4) {
            // 同步追剧
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item_zhuiju, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            ImageView ivLarge = (ImageView) convertView.findViewById(R.id.ivLarge);
            TextView tvTitle1 = (TextView) convertView.findViewById(R.id.tvTitle1);
            TextView tvTitle2 = (TextView) convertView.findViewById(R.id.tvTitle2);
            List<ListBean> list = recommendBean.getList();
            ListBean listBean = list.get(0);
            Glide.with(context).load(listBean.getPic()).placeholder(R.mipmap.ly_default_banner)
                    .error(R.mipmap.ly_default_banner).into(ivLarge);
//            x.image().bind(ivLarge, listBean.getPic());
            ivLarge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了大图！", Toast.LENGTH_SHORT).show();
                }
            });
            tvTitle1.setText(listBean.getTitle());
            tvTitle2.setText(listBean.getDescription());
            textView.setText(recommendBean.getName());
            List<ListBean> list2 = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                list2.add(list.get(i));
            }
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list2);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 5) {
            // 劲爆综艺
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item_zongyi, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            ImageView ivLarge = (ImageView) convertView.findViewById(R.id.ivLarge);
            TextView tvTitle1 = (TextView) convertView.findViewById(R.id.tvTitle1);
            TextView tvTitle2 = (TextView) convertView.findViewById(R.id.tvTitle2);
            List<ListBean> list = recommendBean.getList();
            ListBean listBean = list.get(0);
            Glide.with(context).load(listBean.getPic()).placeholder(R.mipmap.ly_default_banner)
                    .error(R.mipmap.ly_default_banner).into(ivLarge);
//            x.image().bind(ivLarge, listBean.getPic());
            ivLarge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了大图！", Toast.LENGTH_SHORT).show();
                }
            });
            tvTitle1.setText(listBean.getTitle());
            tvTitle2.setText(listBean.getDescription());
            textView.setText(recommendBean.getName());
            List<ListBean> list2 = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                list2.add(list.get(i));
            }
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list2);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 6) {
            // 人气动漫
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item_zongyi, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            ImageView ivLarge = (ImageView) convertView.findViewById(R.id.ivLarge);
            TextView tvTitle1 = (TextView) convertView.findViewById(R.id.tvTitle1);
            TextView tvTitle2 = (TextView) convertView.findViewById(R.id.tvTitle2);
            List<ListBean> list = recommendBean.getList();
            ListBean listBean = list.get(0);
            Glide.with(context).load(listBean.getPic()).placeholder(R.mipmap.ly_default_banner)
                    .error(R.mipmap.ly_default_banner).into(ivLarge);
//            x.image().bind(ivLarge, listBean.getPic());
            ivLarge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了大图！", Toast.LENGTH_SHORT).show();
                }
            });
            tvTitle1.setText(listBean.getTitle());
            tvTitle2.setText(listBean.getDescription());
            textView.setText(recommendBean.getName());
            List<ListBean> list2 = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                list2.add(list.get(i));
            }
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list2);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 7) {
            // 新闻聚焦
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item2, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 8) {
            // 最新影讯
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 9) {
            // 海外剧场
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item3, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 10) {
            // 明星影院
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item_mingxing, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            ImageView ivLarge = (ImageView) convertView.findViewById(R.id.ivLarge);
            TextView tvTitle1 = (TextView) convertView.findViewById(R.id.tvTitle1);
            TextView tvTitle2 = (TextView) convertView.findViewById(R.id.tvTitle2);
            List<ListBean> list = recommendBean.getList();
            ListBean listBean = list.get(0);
            Glide.with(context).load(listBean.getPic()).placeholder(R.mipmap.ly_default_banner)
                    .error(R.mipmap.ly_default_banner).into(ivLarge);
//            x.image().bind(ivLarge, listBean.getPic());
            ivLarge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了大图！", Toast.LENGTH_SHORT).show();
                }
            });
            tvTitle1.setText(listBean.getTitle());
            tvTitle2.setText(listBean.getDescription());
            textView.setText(recommendBean.getName());
            List<ListBean> list2 = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                list2.add(list.get(i));
            }
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list2);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 11) {
            // 哔哔哔哔老电影
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item_bibibi, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 12) {
            // 明星八卦
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 13) {
            // 每日一乐
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item_bibibi, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 14) {
            // 音乐下午茶
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 15) {
            // 专题策划
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item2, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            IndexGridViewAdapter adapter = new IndexGridViewAdapter(context, list);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        if (getItemViewType(position) == 16) {
            // 游戏推荐
            RecommendBean recommendBean = list.get(position-1);
            convertView = inflate.inflate(R.layout.index_list_item_yingyong, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.tvName);
            GridView gridView = (GridView) convertView.findViewById(R.id.gvList);
            textView.setText(recommendBean.getName());
            List<ListBean> list = recommendBean.getList();
            List<ListBean> list2 = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                list2.add(list.get(i));
            }
            IndexGridViewAdapter2 adapter = new IndexGridViewAdapter2(context, list2);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            gridView.setAdapter(adapter);
        }
        return convertView;
    }
}

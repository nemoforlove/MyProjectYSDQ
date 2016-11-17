package com.gpf.myprojectysdq.view.pager;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.adapter.ChannelAdapter;
import com.gpf.myprojectysdq.adapter.ChannelType1Adapter;
import com.gpf.myprojectysdq.adapter.ChannelType2Adapter;
import com.gpf.myprojectysdq.base.BasePager;
import com.gpf.myprojectysdq.bean.ChannelInfo;
import com.gpf.myprojectysdq.bean.ChannelInfo.InfoBean.ChannelBean;
import com.gpf.myprojectysdq.bean.ChannelInfo.InfoBean.CustomBean;
import com.gpf.myprojectysdq.constant.GlobalURL;
import com.gpf.myprojectysdq.utils.PrefUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 首页页面
 */

public class ChannelPager extends BasePager {

    /*@ViewInject(R.id.gvType1)
    private GridView gvType1;
    @ViewInject(R.id.gvType2)
    private GridView gvType2;*/
    @ViewInject(R.id.lvChannel)
    private ListView lvChannel;
    private List<ChannelBean> channel;
    private List<CustomBean> custom;

    public ChannelPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.main_channel, null);
        x.view().inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        String cache = PrefUtils.getString(context, GlobalURL.CHANNEL_URL, "");
        if (!TextUtils.isEmpty(cache)) {
            parseData(cache);
        } else {
            // 获取网络数据
            RequestParams params = new RequestParams(GlobalURL.CHANNEL_URL);
            x.http().get(params, new Callback.CacheCallback<String>() {
                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    //保存数据到缓存
                    PrefUtils.setString(context, GlobalURL.CHANNEL_URL, result);
                    parseData(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
    }

    private void parseData(String result) {
        Gson gson = new Gson();
        ChannelInfo channelInfo = gson.fromJson(result, ChannelInfo.class);
        channel = channelInfo.getInfo().getChannel();
        custom = channelInfo.getInfo().getCustom();
        // 设置适配器
        /*ChannelType1Adapter adapter1 = new ChannelType1Adapter(context, channel);
        gvType1.setAdapter(adapter1);
        ChannelType2Adapter adapter2 = new ChannelType2Adapter(context, custom);
        gvType2.setAdapter(adapter2);*/
        ChannelAdapter adapter = new ChannelAdapter(context,channel,custom);
        lvChannel.setAdapter(adapter);
    }
}

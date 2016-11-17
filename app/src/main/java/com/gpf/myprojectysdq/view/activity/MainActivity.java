package com.gpf.myprojectysdq.view.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.base.BaseActivity;
import com.gpf.myprojectysdq.base.BasePager;
import com.gpf.myprojectysdq.presenter.IMainPresenter;
import com.gpf.myprojectysdq.presenter.MainPresentCompl;
import com.gpf.myprojectysdq.view.pager.ChannelPager;
import com.gpf.myprojectysdq.view.pager.DownloadPager;
import com.gpf.myprojectysdq.view.pager.IndexPager;
import com.gpf.myprojectysdq.view.pager.LocalMovies;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements OnClickListener {

    @ViewInject(R.id.tvSearch)
    private TextView tvSearch;
    @ViewInject(R.id.tvGame)
    private TextView ivGame;
    @ViewInject(R.id.ivHistory)
    private ImageView ivHistory;
    @ViewInject(R.id.rgMainTabs)
    private RadioGroup rgTabs;
    @ViewInject(R.id.drawer)
    private DrawerLayout drawer;
    @ViewInject(R.id.navigation)
    private NavigationView navigation;
    private IMainPresenter mainPresenter;
    private List<BasePager> list;
    private int currentPager;
    private ImageView headerIcon;
    private TextView tv;

    @Override
    public void setStyle() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mainPresenter = new MainPresentCompl(this,new Intent());
        x.view().inject(this);
        String name = getIntent().getStringExtra("name");
        if(name != null){
            tv.setText(name);
        }
    }

    @Override
    public void getMethod() {
        // 默认显示首页内容，此时currentPager的值为0
        mainPresenter.setIntoFragment(list,currentPager);
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        list.add(new IndexPager(this)); //首页
        list.add(new ChannelPager(this));//频道
        list.add(new DownloadPager(this));//离线
        list.add(new LocalMovies(this));//我的
    }

    @Override
    public void setListener() {
        tvSearch.setOnClickListener(this);
        ivGame.setOnClickListener(this);
        ivHistory.setOnClickListener(this);
        // 为按钮组设置监听
        rgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_tab_index:
                        currentPager = 0;
                        break;
                    case R.id.main_tab_channel:
                        currentPager = 1;
                        break;
                    case R.id.main_tab_download:
                        currentPager = 2;
                        break;
                    case R.id.main_tab_me:
                        currentPager = 3;
                        break;
                    default:
                        currentPager = 0;
                        break;
                }
                // 调用将页面放入Fragmemnt中的逻辑
                mainPresenter.setIntoFragment(list,currentPager);
            }
        });
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.item01:
                        // 留言板
//                        Toast.makeText(MainActivity.this, "功能未开启！", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,LiuyanbanActivity.class));
                        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        break;
                    case R.id.item02:
                        // 我的离线
                        Toast.makeText(MainActivity.this, "系统维护中！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item03:
                        // 本地
                        startActivity(new Intent(MainActivity.this,LocalActivity.class));
                        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        break;
                    case R.id.item04:
                        // 播放记录
                        startActivity(new Intent(MainActivity.this,HistoryActivity.class));// 跳转
//                        Toast.makeText(MainActivity.this, "diandddd", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        // 点击头像登录
        View headerView = navigation.getHeaderView(0);
        headerIcon = (ImageView) headerView.findViewById(R.id.LoginImage);
        tv = (TextView) headerView.findViewById(R.id.tvName3);

        headerIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.tvSearch:
               // 跳转到搜索界面
//               Toast.makeText(MainActivity.this, "搜索功能维护，请稍后重试！", Toast.LENGTH_SHORT).show();
               mainPresenter.forwardSearch();
               break;
           case R.id.tvGame:
               // 跳转到游戏界面
               Toast.makeText(MainActivity.this, "游戏功能维护，请稍后重试！", Toast.LENGTH_SHORT).show();
//               mainPresenter.forwardGame();
               break;
           case R.id.ivHistory:
               // 跳转到播放记录页面
//               Toast.makeText(MainActivity.this, "暂无播放记录！", Toast.LENGTH_SHORT).show();
               mainPresenter.forwardHistory();
               break;
       }
    }

    @Override
    protected void onDestroy() {
        mainPresenter = null;
        super.onDestroy();
    }
}

package com.gpf.myprojectysdq.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.gpf.myprojectysdq.R;
import com.gpf.myprojectysdq.app.App;
import com.gpf.myprojectysdq.base.BaseActivity;
import com.gpf.myprojectysdq.bean.Search;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.searchBack)
    private ImageView searchBack;
    @ViewInject(R.id.searchEdit)
    private EditText searchEdit;
    @ViewInject(R.id.searchSearch)
    private ImageView searchSearch;
    @ViewInject(R.id.searchClear)
    private ImageView searchClear;
    @ViewInject(R.id.searchListView)
    private ListView searchLv;
    @ViewInject(R.id.searchDel)
    private ImageView searchDel;

    private DbManager db =null;
    private List<Search> all = null;
    private List<String> list = null;
    private ArrayAdapter<String> adapter = null;
    private Search search =null;

            Intent intent;
    private List<Search> strList = new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        x.view().inject(this);
        intent = new Intent();
        db =x.getDb(((App) getApplicationContext()).getDaoConfig());
        search = new Search();
    }

    @Override
    public void getMethod() {
//        Intent intent2 = getIntent();
//        String reKey = intent2.getStringExtra("reKey");


    }

    @Override
    public void setStyle() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
    }

    @Override
    public void setListener() {
        searchBack.setOnClickListener(this);
        searchSearch.setOnClickListener(this);
        searchClear.setOnClickListener(this);
        goSearch();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.searchBack:
                finish();
                break;
            case R.id.searchClear:
                searchEdit.setText("");
                break;
            case R.id.searchSearch:
                String searchStr = searchEdit.getText().toString();
                if (TextUtils.isEmpty(searchStr)) {
                    Toast.makeText(SearchActivity.this, "请输入搜索词", Toast.LENGTH_LONG).show();
                } else {
                    playKey(searchStr);
                }

                //点击搜索按钮将输入文本框的内容添加进入书库
//                strList.add(search);
//                DbManager db = x.getDb(((App) getApplicationContext()).getDaoConfig());
                try {
                    List<Search> name = db.selector(Search.class).where("name", "=", searchStr).findAll();
                    if(name.size()>0){

                        search.setName(searchStr);
                        try {
                            db.save(search);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.searchDel:
                try {

                    list.clear();
                    adapter.notifyDataSetChanged();
                    db.delete(Search.class);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;

        }

    }

    private void playKey(String searchStr) {
        intent.putExtra("key", searchStr);
        intent.setClass(SearchActivity.this, SearchGoActivity.class);
        startActivity(intent);
    }

    @Override
    public void initData() {
        //每次页面加载出来，以前的搜索记录都会显示在上面，即从数据库里面把数据取出来展示在上面
        super.initData();
//        final DbManager db = x.getDb(((App) getApplicationContext()).getDaoConfig());

        try {
            all =db.findAll(Search.class);
            list = new ArrayList<>();
            for (int i= 0;i<all.size();i++){
                list.add(all.get(i).getName());
            }
            if( all==null){

            }else {
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
                searchLv.setVisibility(View.VISIBLE);
                searchLv.setAdapter(adapter);

                //listView的条目点击进行播放
                searchLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(SearchActivity.this,"长按删除",Toast.LENGTH_LONG).show();
                        playKey(list.get(position));
                    }
                });
                //当长按listView的条目的时候，进行删除
                searchLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            db.deleteById(Search.class,all.get(position).getId());
                            list.remove(all.get(position).getName());
                            adapter.notifyDataSetChanged();
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                });

            }
        } catch (DbException e) {

        }


    }

    //输入框内容的判断
    private void goSearch() {
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    searchClear.setVisibility(View.VISIBLE);
                } else {
                    searchClear.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


}

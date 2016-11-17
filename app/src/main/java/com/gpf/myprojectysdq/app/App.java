package com.gpf.myprojectysdq.app;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/8.
 */

public class App extends Application {

    private DbManager.DaoConfig daoConfig;

    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    UMShareAPI umShareAPI;

    public UMShareAPI getUmShareAPI() {
        return umShareAPI;
    }

    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("1105724767", "hUogtYgHTcxoXf43");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);// 初始化xutils
        umShareAPI = UMShareAPI.get(this);
        daoConfig = new DbManager.DaoConfig()
                .setDbName("ysdq.db")
                .setDbVersion(1)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
    }
}

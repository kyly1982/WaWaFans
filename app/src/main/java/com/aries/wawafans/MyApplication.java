package com.aries.wawafans;

import android.app.Application;

import com.aries.wawafans.Bean.User;
import com.aries.wawafans.UTILS.UserProfile;
import com.umeng.commonsdk.UMConfigure;

/**
 * Created by kyly on 2017/11/27.
 */

public class MyApplication extends Application {
    private User user;

    public static MyApplication instence;

    @Override
    public void onCreate() {
        instence = this;
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void initUMAnalytics() {

    }

    private void initUMShare() {

    }

    private void initUMPush() {
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
    }

    public boolean isLogin() {
        return null != user && user.isUserValid();
    }

    public void handleLogin(User user) {
        this.user = user;
    }

    public void handleExie() {
        UserProfile.saveUserInfo();
        user = null;
    }

    public User getUser() {
        return user;
    }
}

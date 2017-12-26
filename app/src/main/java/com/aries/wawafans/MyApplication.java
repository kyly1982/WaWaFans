package com.aries.wawafans;

import android.app.Application;

import com.aries.wawafans.Bean.User;
import com.aries.wawafans.UTILS.UserProfile;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.umeng.commonsdk.UMConfigure;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by kyly on 2017/11/27.
 */

public class MyApplication extends Application {
    private User user;

    public static MyApplication instence;

    @Override
    public void onCreate() {
        instence = this;
        initOkGo();
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

    private void initOkGo() {
        OkGo.getInstance().init(this);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //配置打印日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        //配置各种超时
        //全局的读取超时时间
        builder.readTimeout(15000, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(15000, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(30000, TimeUnit.MILLISECONDS);
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

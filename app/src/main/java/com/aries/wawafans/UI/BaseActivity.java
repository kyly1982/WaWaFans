package com.aries.wawafans.UI;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aries.wawafans.MyApplication;
import com.aries.wawafans.R;


/**
 * Created by kyly on 2017/11/27.
 * 基础类，用于处理需要统一处理或者每个activity中特定生命周期中均需要处理的事件
 * 提供一些公用的方法
 */

public class BaseActivity extends AppCompatActivity {
    private View rootView;
    protected MyApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = MyApplication.instence;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        application = MyApplication.instence;
    }

    void showMessage(String msg) {
        if (null == rootView) {
            rootView = findViewById(android.R.id.content);
        }
        Snackbar snackbar = Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundResource(R.color.colorMessage);
        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.primaryTextColor));
        snackbar.show();
    }

    void showError(String error) {
        if (null == rootView) {
            rootView = findViewById(android.R.id.content);
        }
        Snackbar snackbar = Snackbar.make(rootView, error, Snackbar.LENGTH_LONG);
        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.primaryTextColor));
        snackbar.getView().setBackgroundResource(R.color.colorError);
        snackbar.show();
    }

    void showError(String error, String action, View.OnClickListener listener) {
        if (null == rootView) {
            rootView = findViewById(android.R.id.content);
        }
        Snackbar snackbar = Snackbar.make(rootView, error, Snackbar.LENGTH_LONG)
                .setAction(action, listener)
                .setActionTextColor(getResources().getColor(R.color.colorWhite));
        snackbar.getView().setBackgroundResource(R.color.colorError);
        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.primaryTextColor));
        snackbar.show();
    }

    void showWarning(String warningMeg) {
        if (null == rootView) {
            rootView = findViewById(android.R.id.content);
        }
        Snackbar snackbar = Snackbar.make(rootView, warningMeg, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundResource(R.color.colorWarning);
        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.primaryTextColor));
        snackbar.show();
    }


}

package com.aries.wawafans.UTILS;

import android.support.annotation.NonNull;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kyly on 2017/11/29.
 */

public class NetTime {
    public static enum NTP_Provider {
        BaiDu("http://www.baidu.com"),
        TaoBao("http://www.taobao.com"),
        NTSC("http://www.ntsc.ac.cn");

        private String value;

        private NTP_Provider(String value) {
            this.value = value;
        }

        public String rawValue() {
            return value;
        }
    }

    public interface OnGetTimeListener {
        void onGetTime(final long time);
    }

    public static void getTime(@NonNull final NTP_Provider provider, @NonNull final OnGetTimeListener listener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(provider.rawValue());
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    listener.onGetTime(connection.getDate());
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onGetTime(-1);
                }
            }
        });
        thread.start();

    }

}

package com.aries.wawafans.UTILS;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by kyly on 2017/12/26.
 */

public class Net {
    public static String server_url="http://www.wawafans.com/";
    public static String price_url = server_url+"price/list";
    public static String order_product_url = server_url+"order/product";
    public static String order_status_url = server_url+"order/status";

    public interface ExampleCallBack {
        void onSuccess(String data);

        void onFail(String msg);
    }

    public interface PayCallBack{
        void onSuccess(int type,String data);
        void onFail(int type,String msg);
    }



    public void httpPost(String url, final int type,Hashtable<String,String> param,final PayCallBack callBack){
        HttpParams params = new HttpParams();
        if(param!=null&&param.size()>0){
            for(Map.Entry<String,String> entry:param.entrySet()){
                params.put(entry.getKey(),entry.getValue());
            }
        }
        OkGo.<String>post(url)
                .params(params)
                .tag(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null != callBack) {
                            callBack.onSuccess(type,response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (null != callBack) {
                            callBack.onFail(type,response.message());
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                    }
                });
    }

    public void example(String url, String params1, int params2, final ExampleCallBack callBack) {
        HttpParams params = new HttpParams();
        params.put("p1", params1);
        params.put("p2", params2);
        OkGo.<String>get(url)
                .params(params)
                .tag(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null != callBack) {
                            callBack.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (null != callBack) {
                            callBack.onFail(response.message());
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                    }
                });
    }
}

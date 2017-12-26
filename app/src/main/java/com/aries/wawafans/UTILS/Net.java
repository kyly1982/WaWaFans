package com.aries.wawafans.UTILS;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by kyly on 2017/12/26.
 */

public class Net {
    public interface ExampleCallBack {
        void onSuccess(String data);

        void onFail(String msg);
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
                            callBack.onSuccess(".....");
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

package com.aries.wawafans.UTILS;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.tauth.Tencent;

/**
 * Created by cdj on 2017/12/6.
 */

public class StaticUtil {
        public final static String WX_Appid = "123";
        public static IWXAPI wx_api; //全局的微信api对象
        public final static String QQ_Appid = "123";
        public static Tencent tencent;//全局的QQ对象
}
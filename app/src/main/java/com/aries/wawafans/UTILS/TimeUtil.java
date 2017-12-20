package com.aries.wawafans.UTILS;

/**
 * Created by kyly on 2017/12/19.
 */

public class TimeUtil {
    public static String getFriendTime(long time) {
        time = System.currentTimeMillis() - time;
        time = time / 60000;//将时间差转换为分钟
        if (time <= 1) {
            return "刚刚";
        } else if (time < 60) {
            return time + "分钟前";
        } else {
            time = time / 60;//小时
            if (time < 24) {
                return time + "小时前";
            } else {
                time = time / 24;//天
                if (time < 30) {
                    if (time == 1) {
                        return "昨天";
                    } else {
                        return time + "天前";
                    }
                } else {
                    time = time / 30;
                    if (time < 12) {
                        if (1 == time) {
                            return "上月";
                        } else {
                            return time + "个月前";
                        }
                    } else {
                        time = time / 12;
                        switch ((int) time) {
                            case 1:
                                return "去年";
                            default:
                                return time + "年前";
                        }
                    }
                }
            }
        }
    }
}

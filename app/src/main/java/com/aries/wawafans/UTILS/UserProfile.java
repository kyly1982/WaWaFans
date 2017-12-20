package com.aries.wawafans.UTILS;

import android.content.Context;
import android.content.SharedPreferences;

import com.aries.wawafans.Bean.User;
import com.aries.wawafans.R;

/**
 * Created by kyly on 2017/11/29.
 */

public class UserProfile {
    private static boolean isFirstBoot = true;
    private static final String fileName = "data";
    private static final String key_isFirstBoot = "firstBoot";
    private static final String key_id = "id";
    private static final String key_level = "level";
    private static final String key_type = "type";
    private static final String key_gender = "gender";
    private static final String key_expired = "expired";
    private static final String key_name = "name";
    private static final String key_nick = "nick";
    private static final String key_portrait = "portrait";
    private static final String key_openId = "openId";
    private static final String key_accessToken = "accessToken";
    private static final String key_imToken = "imToken";
    private static final String key_address = "address";
    private static final String key_zip = "zip";
    private static final String key_telephone = "telephone";

    public static User loadUserInfo(Context context) {
        User user = null;
        SharedPreferences preferences = context.getSharedPreferences(fileName, 0);
        isFirstBoot = preferences.getBoolean(key_isFirstBoot, true);
        if (!isFirstBoot) {
            user = new User();
            user.setId(preferences.getInt(key_id, 0));
            user.setLevel(preferences.getInt(key_level, 0));
            user.setType(preferences.getInt(key_type, 0));
            user.setGender(preferences.getInt(key_gender, 0));
            user.setExpiredTime(preferences.getLong(key_expired, 0l));
            user.setName(preferences.getString(key_name, null));
            user.setNick(preferences.getString(key_nick, null));
            user.setPortrait(preferences.getString(key_portrait, null));
            user.setOpenId(preferences.getString(key_openId, null));
            user.setAccessToken(preferences.getString(key_accessToken, null));
            user.setImToken(preferences.getString(key_imToken, null));
            user.setAddress(preferences.getString(key_address, null));
            user.setZipCode(preferences.getInt(key_zip, 0));
            user.setTelephone(preferences.getString(key_telephone, null));
        }
        return user;
    }

    public static void saveUserInfo() {

    }

}

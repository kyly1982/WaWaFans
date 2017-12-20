package com.aries.wawafans.Bean;

/**
 * Created by kyly on 2017/11/27.
 */

public class User {
    private int id;
    private int level;
    private int type;   //0:自注册，1：微信，2：QQ
    private int gender; //0：男，1：女，2:其它
    private long expiredTime;   //过期日期
    private String name;    //姓名，一般也做openid
    private String nick;    //昵称
    private String portrait;//头像
    private String openId;  //开放平台的id
    private String accessToken;//开放平台的token
    private String imToken; //聊天token
    private String address; //收货地址
    private int zipCode; //邮编
    private String telephone;//联系电话

    public User() {
    }

    public User(String name, String nick) {
        this.name = name;
        this.nick = nick;
    }

    public User(int id, String name, String nick) {
        this.id = id;
        this.name = name;
        this.nick = nick;
    }

    public User(String openId, String name, String nick, int gender, String accessToken, long expiredTime) {
        this.gender = gender;
        this.expiredTime = expiredTime;
        this.name = name;
        this.nick = nick;
        this.openId = openId;
        this.accessToken = accessToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public boolean isUserValid() {
        return expiredTime > System.currentTimeMillis();
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }


}

package com.aries.wawafans.Bean;

/**
 * Created by kyly on 2017/11/27.
 */

public class User {
    private int id;
    private int level;
    private int type;
    private int gender;
    private long expiredTime;
    private String name;
    private String nick;
    private String portrait;
    private String openId;
    private String accessToken;
    private String imToken;
    private String address;
    private String zipCode;
    private String telephone;

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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        zipCode = zipCode;
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

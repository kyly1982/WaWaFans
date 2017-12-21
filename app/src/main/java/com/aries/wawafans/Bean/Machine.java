package com.aries.wawafans.Bean;

import java.io.Serializable;

/**
 * Created by kyly on 2017/11/27.
 * 机器信息
 */

public class Machine implements Serializable {
    private int id;
    private int port;
    private int key = 9352;
    private short price;
    private short state;
    private short time = 20;
    private String name;
    private String desc;
    private String cover;
    private String host;
    private String watchVideo;
    private String realTimeVideo;

    public Machine(int id, String name, String desc, String host, int port, short price) {
        this.id = id;
        this.port = port;
        this.price = price;
        this.name = name;
        this.desc = desc;
        this.host = host;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getPrice() {
        return price;
    }

    public void setPrice(short price) {
        this.price = price;
    }

    public short getState() {
        return state;
    }

    public void setState(MachineStates state) {
        this.state = state.getValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setState(short state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getWatchVideo() {
        return watchVideo;
    }

    public void setWatchVideo(String watchVideo) {
        this.watchVideo = watchVideo;
    }

    public String getRealTimeVideo() {
        return realTimeVideo;
    }

    public void setRealTimeVideo(String realTimeVideo) {
        this.realTimeVideo = realTimeVideo;
    }

    public short getTime() {
        return time;
    }

    public void setTime(short time) {
        this.time = time;
    }
}



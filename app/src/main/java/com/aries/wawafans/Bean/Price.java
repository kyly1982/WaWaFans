package com.aries.wawafans.Bean;

/**
 * Created by kyly on 2017/12/25.
 */

public class Price {
    int id;
    int coin;
    int rmb;

    public Price(int id,int coin, int rmb) {
        this.id = id;
        this.coin = coin;
        this.rmb = rmb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getRmb() {
        return rmb;
    }

    public void setRmb(int rmb) {
        this.rmb = rmb;
    }
}

package com.aries.wawafans.Bean;

/**
 * Created by kyly on 2017/11/27.
 * 用户帐单，记录用户的充值消费情况
 * 充值总金额=累积消费金额+当前可用金额
 * 用户因开号赠送、充值赠送、游戏打折等获利均计入折扣总金额中
 * 兑换奖品次数指用户将小物品换成大物品等情况
 * 兑现奖品指用户要求邮寄奖品
 */

public class Bill {
    private int id;
    private int amount;         //充值总金额
    private int available;      //当前可用金额
    private int consumption;    //累积消费金额
    private int discount;       //折扣总金额
    private int shippingAmount; //邮资总额
    private int freeShippingAmount;   //包邮邮资总额

    private int playCount;      //游戏次数
    private int winCount;       //获胜次数
    private int exchangeCount;  //兑换奖品次数
    private int cashCount;      //兑现奖品次数


    public Bill() {
    }

    public Bill(int amount, int available, int consumption, int discount, int shippingAmount, int freeShippingAmount, int playCount, int winCount, int exchangeCount, int cashCount) {
        this.amount = amount;
        this.available = available;
        this.consumption = consumption;
        this.discount = discount;
        this.shippingAmount = shippingAmount;
        this.freeShippingAmount = freeShippingAmount;
        this.playCount = playCount;
        this.winCount = winCount;
        this.exchangeCount = exchangeCount;
        this.cashCount = cashCount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getExchangeCount() {
        return exchangeCount;
    }

    public void setExchangeCount(int exchangeCount) {
        this.exchangeCount = exchangeCount;
    }

    public int getCashCount() {
        return cashCount;
    }

    public void setCashCount(int cashCount) {
        this.cashCount = cashCount;
    }

    public int getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(int shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public int getFreeShippingAmount() {
        return freeShippingAmount;
    }

    public void setFreeShippingAmount(int freeShippingAmount) {
        this.freeShippingAmount = freeShippingAmount;
    }
}

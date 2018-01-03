package com.aries.wawafans.Bean;

/**
 * Created by cdj on 2017/12/26.
 */

public class PayOrder {
    private String outtradeno;
    private String userId;
    private long insertTime;
    private long payTime;
    private Integer payCoin;
    private Integer totalAmount;
    private String tradeNo;
    private String channel;
    private Integer orderStatus;
    private Integer priceId;

    public PayOrder(String outtradeno, String userId, long insertTime, long payTime, Integer payCoin, Integer totalAmount, String tradeNo, String channel, Integer orderStatus, Integer priceId) {
        this.outtradeno = outtradeno;
        this.userId = userId;
        this.insertTime = insertTime;
        this.payTime = payTime;
        this.payCoin = payCoin;
        this.totalAmount = totalAmount;
        this.tradeNo = tradeNo;
        this.channel = channel;
        this.orderStatus = orderStatus;
        this.priceId = priceId;
    }

    public String getOuttradeno() {
        return outtradeno;
    }

    public void setOuttradeno(String outtradeno) {
        this.outtradeno = outtradeno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public Integer getPayCoin() {
        return payCoin;
    }

    public void setPayCoin(Integer payCoin) {
        this.payCoin = payCoin;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }
}

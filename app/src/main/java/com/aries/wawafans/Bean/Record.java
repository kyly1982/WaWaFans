package com.aries.wawafans.Bean;

/**
 * Created by kyly on 2017/12/19.
 */

public class Record {
    private int id;
    private int itemId;
    private long time;
    private String itemName;
    private String itemCover;

    public Record(int id, int itemId, long time, String itemName, String itemCover) {
        this.id = id;
        this.itemId = itemId;
        this.time = time;
        this.itemName = itemName;
        this.itemCover = itemCover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCover() {
        return itemCover;
    }

    public void setItemCover(String itemCover) {
        this.itemCover = itemCover;
    }

}

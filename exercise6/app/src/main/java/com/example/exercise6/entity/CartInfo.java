package com.example.exercise6.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_info")
public class CartInfo  {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int goodsId;
    public int count;
    public CartInfo() {}
    public CartInfo(int id,int goodsId,int count) {
        this.id = id;
        this.goodsId = goodsId;
        this.count = count;
    }
}

package com.example.exercise6.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.exercise6.R;

import java.util.ArrayList;

@Entity(tableName = "goods_info")
public class GoodsInfo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String description;
    public float price;
    public String picPath;
    public int pic;
    private static String[] mNameArray = {
            "iPhone11","Mate30","小米10","OPPO Reno3","vivo x30","荣耀30s"
    };
    private static String[] mDescArray = {
            "Apple iPhone11 256GB 绿色 4G全网通手机",
            "华为 HUAWEI Mate30 8GB+256GB 丹霞橙 5G全网通手机 全面屏手机",
            "小米 MI10 8GB+128GB 钛银黑 5G手机 游戏拍照手机",
            "OPPO Reno3 8GB+128GB 蓝色星夜 双模5G 拍照游戏智能手机",
            "vivo X30 8GB+128GB 绯云 5G全网通 美颜拍照手机",
            "荣耀30S 8GB+128GB 蝶羽红 5G芯片 自拍全面屏手机"
    };
    private static float[] mPriceArray = {6299,4999,3999,2999,2998,2399};
    private static int[] mPicArray = {
            R.drawable.iphone,R.drawable.huawei,R.drawable.xiaomi,
            R.drawable.oppo,R.drawable.vivo,R.drawable.rongyao
    };
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList =  new ArrayList<GoodsInfo>();
        for(int i = 0;i<mNameArray.length;i++)  {
            GoodsInfo info = new GoodsInfo();
            info.id = i;
            info.name = mNameArray[i];
            info.description = mDescArray[i];
            info.price = mPriceArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}

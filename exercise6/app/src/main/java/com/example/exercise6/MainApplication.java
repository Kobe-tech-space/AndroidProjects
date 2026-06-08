package com.example.exercise6;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;


import com.example.exercise6.database.ShoppingDBHelper;
import com.example.exercise6.entity.GoodsInfo;
import com.example.exercise6.util.FileUtil;
import com.example.exercise6.util.SharedUtil;

import java.io.File;
import java.util.List;

public class MainApplication extends Application {
    public int goodsCount;
    private static MainApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MainApplication","onCreate");
        mInstance = this;
        initGoodsInfo();
    }
    public static MainApplication getInstance() {
        return mInstance;
    }
    private void initGoodsInfo() {
        boolean isFirst = SharedUtil.getInstance(this).readBoolean("first",true);
        String directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
                + File.separator;
        if (isFirst) {
            List<GoodsInfo> list = GoodsInfo.getDefaultList();
            for (GoodsInfo info : list) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),info.pic);
                String path = directory + info.id + ".jpg";
                FileUtil.saveImage(path,bitmap);
                bitmap.recycle();
                info.picPath = path;
            }
            ShoppingDBHelper dbHelper = ShoppingDBHelper.getInstance(this);
            dbHelper.openWriteLink();
            dbHelper.insertGoodsInfos(list);
            dbHelper.closeLink();
            SharedUtil.getInstance(this).writeBoolean("first",false);
        }
    }
}

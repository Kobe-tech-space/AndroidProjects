package com.example.exercise6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exercise6.database.ShoppingDBHelper;
import com.example.exercise6.entity.GoodsInfo;
import com.example.exercise6.util.ToastUtil;

public class ShoppingDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_title;
    private TextView tv_count;
    private TextView tv_goods_price;
    private TextView tv_goods_desc;
    private ImageView iv_goods_pic;
    private ShoppingDBHelper mDBHelper;
    private int mGoodsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_detail);
        tv_title = findViewById(R.id.tv_title);
        tv_count = findViewById(R.id.tv_count);
        tv_goods_price = findViewById(R.id.tv_goods_price);
        tv_goods_desc = findViewById(R.id.tv_goods_desc);
        iv_goods_pic = findViewById(R.id.iv_goods_pic);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);
        findViewById(R.id.btn_add_cart).setOnClickListener(this);
        tv_count.setText(String.valueOf(MainApplication.getInstance().goodsCount));
        mDBHelper = ShoppingDBHelper.getInstance(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        showDetail();
    }
    private void showDetail() {
        mGoodsId = getIntent().getIntExtra("goods_id",0);
        if (mGoodsId>0) {
            GoodsInfo info = mDBHelper.queryGoodsInfoById(mGoodsId);
            tv_title.setText(info.name);
            tv_goods_desc.setText(info.description);
            tv_goods_price.setText(String.valueOf((int) info.price));
            iv_goods_pic.setImageURI(Uri.parse(info.picPath));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_cart:
                Intent intent = new Intent(this,ShoppingCartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_add_cart:
                addToCart(mGoodsId);
                break;
        }
    }
    private void addToCart(int goodsID) {
        int count = ++MainApplication.getInstance().goodsCount;
        tv_count.setText(String.valueOf(count));
        mDBHelper.insertCartInfo(goodsID);
        ToastUtil.show(this,"成功添加至购物车");
    }
}
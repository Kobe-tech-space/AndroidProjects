package com.example.exercise6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exercise6.database.ShoppingDBHelper;
import com.example.exercise6.entity.CartInfo;
import com.example.exercise6.entity.GoodsInfo;
import com.example.exercise6.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_count;
    private LinearLayout ll_cart;
    private ShoppingDBHelper mDBHelper;
    private List<CartInfo> mCartList;
    private Map<Integer, GoodsInfo> mGoodsMap = new HashMap<>();
    private TextView tv_total_price;
    private LinearLayout ll_empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("购物车");
        ll_cart = findViewById(R.id.ll_cart);
        tv_total_price = findViewById(R.id.tv_total_price);
        tv_count = findViewById(R.id.tv_count);
        tv_count.setText(String.valueOf(MainApplication.getInstance().goodsCount));
        mDBHelper = ShoppingDBHelper.getInstance(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.btn_shopping_channel).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_settle).setOnClickListener(this);
        ll_empty = findViewById(R.id.ll_empty);
    }
    @Override
    protected void onResume() {
        super.onResume();
        showCart();
    }
    private void showCart() {
        ll_cart.removeAllViews();
        mCartList = mDBHelper.queryAllCartInfo();
        if (mCartList.size() == 0) {
            ll_empty.setVisibility(View.VISIBLE);
            ll_cart.setVisibility(View.GONE);
            tv_total_price.setText("0");
            return;
        }
        ll_empty.setVisibility(View.GONE);
        ll_cart.setVisibility(View.VISIBLE);
        for (CartInfo info : mCartList) {
            GoodsInfo goods = mDBHelper.queryGoodsInfoById(info.goodsId);
            mGoodsMap.put(info.goodsId, goods);
            View view = LayoutInflater.from(this).inflate(R.layout.item_cart, null);
            ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_desc = view.findViewById(R.id.tv_desc);
            TextView tv_count = view.findViewById(R.id.tv_count);
            TextView tv_price = view.findViewById(R.id.tv_price);
            TextView tv_sum = view.findViewById(R.id.tv_sum);
            iv_thumb.setImageURI(Uri.parse(goods.picPath));
            tv_name.setText(goods.name);
            tv_desc.setText(goods.description);
            tv_count.setText(String.valueOf(info.count));
            tv_price.setText(String.valueOf((int) goods.price));
            tv_sum.setText(String.valueOf((int) (info.count * goods.price)));

            view.setOnLongClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                builder.setMessage("是否从购物车删除" + goods.name + "？");
                builder.setPositiveButton("是", (dialog, which) -> {
                    ll_cart.removeView(v);
                    deleteGoods(info);
                });
                builder.setNegativeButton("否", null);
                builder.create().show();
                return true;
            });

            view.setOnClickListener(v -> {
                Intent intent = new Intent(ShoppingCartActivity.this, ShoppingDetailActivity.class);
                intent.putExtra("goods_id", goods.id);
                startActivity(intent);
            });

            ll_cart.addView(view);
        }
        refreshTotalPrice();
    }
    private void deleteGoods(CartInfo info) {
        MainApplication.getInstance().goodsCount -= info.count;
        mDBHelper.deleteCartInfoByGoodsId(info.goodsId);
        CartInfo removed = null;
        for (CartInfo cartInfo : mCartList) {
            if(cartInfo.goodsId == info.goodsId) {
                removed = cartInfo;
                break;
            }
        }
        mCartList.remove(removed);
        showCount();
        ToastUtil.show(this,"已从购物车删除" + mGoodsMap.get(info.goodsId).name);
        refreshTotalPrice();
    }
    private void showCount() {
        tv_count.setText(String.valueOf(MainApplication.getInstance().goodsCount));
        if (MainApplication.getInstance().goodsCount == 0) {
            ll_empty.setVisibility(View.VISIBLE);
            ll_cart.removeAllViews();
        } else {
            ll_empty.setVisibility(View.GONE);
        }
    }
    private void refreshTotalPrice() {
        int totalPrice = 0;
        for (CartInfo info : mCartList) {
            GoodsInfo goods = mGoodsMap.get(info.goodsId);
            if (goods != null) {
                totalPrice += goods.price * info.count;
            }
        }
        tv_total_price.setText(String.valueOf(totalPrice));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_shopping_channel:
                Intent intent = new Intent(this,ShoppingChannelActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.btn_settle:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("结算商品");
                builder.setMessage("客官抱歉,支付功能尚未开通,请下次再来");
                builder.setPositiveButton("我知道了",null);
                builder.create().show();
                break;
            case R.id.btn_clear:
                mDBHelper.deleteAllCartInfo();
                MainApplication.getInstance().goodsCount=0;
                showCount();
                ToastUtil.show(this,"购物车已清空");
        }
    }
}
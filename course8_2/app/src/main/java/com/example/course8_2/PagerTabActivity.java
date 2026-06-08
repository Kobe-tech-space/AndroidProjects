package com.example.course8_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;

import com.example.course8_2.adapter.ImagePagerAdapater;
import com.example.course8_2.entity.GoodsInfo;
import com.example.course8_2.util.ToastUtil;

import java.util.ArrayList;

public class PagerTabActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ArrayList<GoodsInfo> mGoodsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_tab);
        initPagerStrip();
        initViewPager();

    }
    private void initPagerStrip() {
        PagerTabStrip pts_tab = findViewById(R.id.pts_tab);
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        pts_tab.setTextColor(Color.BLACK);
    }
    private void initViewPager() {
        ViewPager vp_content = findViewById(R.id.vp_content);
        mGoodsList = GoodsInfo.getDefaultList();
        ImagePagerAdapater adapter = new ImagePagerAdapater(this,mGoodsList);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(this);
        vp_content.setCurrentItem(3);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        ToastUtil.show(this,"您翻到的手机品牌是：" + mGoodsList.get(position).name);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
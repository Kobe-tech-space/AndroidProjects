package com.example.course8_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.course8_2.adapter.ImagePagerAdapater;
import com.example.course8_2.entity.GoodsInfo;
import com.example.course8_2.util.ToastUtil;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ArrayList<GoodsInfo> mGoodsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ViewPager vp_content = findViewById(R.id.vp_content);
        mGoodsList = GoodsInfo.getDefaultList();
        ImagePagerAdapater adapter = new ImagePagerAdapater(this,mGoodsList);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        ToastUtil.show(this,"您翻到的品牌是：" + mGoodsList.get(position).name);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
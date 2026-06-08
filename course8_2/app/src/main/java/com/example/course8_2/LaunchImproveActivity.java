package com.example.course8_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.course8_2.adapter.LaunchImproveAdapter;
import com.example.course8_2.adapter.LaunchSimpleAdapter;

public class LaunchImproveActivity extends AppCompatActivity {
    private int[] lanuchImageArray = {R.drawable.guide_bg1,R.drawable.guide_bg2,
            R.drawable.guide_bg3,R.drawable.guide_bg4,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_improve);
        ViewPager vp_launch = findViewById(R.id.vp_launch);
        LaunchImproveAdapter adapter = new LaunchImproveAdapter(
                getSupportFragmentManager(),lanuchImageArray);
        vp_launch.setAdapter(adapter);
    }
}
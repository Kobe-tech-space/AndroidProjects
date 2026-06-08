package com.example.course8_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.course8_2.adapter.LaunchSimpleAdapter;

public class LaunchSimpleActivity extends AppCompatActivity {
    private int[] lanuchImageArray = {R.drawable.guide_bg1,R.drawable.guide_bg2,
            R.drawable.guide_bg3,R.drawable.guide_bg4,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_simple);
        ViewPager vp_launch = findViewById(R.id.vp_launch);
        LaunchSimpleAdapter adapter = new LaunchSimpleAdapter(this,lanuchImageArray);
        vp_launch.setAdapter(adapter);
    }
}
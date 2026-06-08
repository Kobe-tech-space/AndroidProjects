package com.example.course32;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.course32.util.DateUtil;

public class ButtonEnableActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_enable;
    private Button btn_test;
    private TextView tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_enable);
        btn_enable=findViewById(R.id.btn_enable);
        Button btn_disable=findViewById(R.id.btn_disable);
        btn_test=findViewById(R.id.btn_test);
        tv_result=findViewById(R.id.tv_result);
        btn_enable.setOnClickListener(this);
        btn_disable.setOnClickListener(this);
        btn_test.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enable:
                btn_test.setEnabled(true);
                btn_test.setTextColor(Color.BLACK);
                break;
            case R.id.btn_disable:
                btn_test.setEnabled(false);
                btn_test.setTextColor(Color.GRAY);
                break;
            case R.id.btn_test:
                String desc=String.format("%s 您点击了按钮：%s", DateUtil.getNowTime(),((Button) v).getText());
                tv_result.setText(desc);
                break;
        }
    }
}
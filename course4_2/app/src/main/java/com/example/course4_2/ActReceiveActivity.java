package com.example.course4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActReceiveActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_receive);
        tv_receive=findViewById(R.id.tv_receive);
        Bundle bundle=getIntent().getExtras();
        String request_time= bundle.getString("request_time");
        String request_content=bundle.getString("request_content");
        String desc=String.format("收到请求消息:\n请求时间为:%s\n请求内容为:%s",request_time,request_content);
        tv_receive.setText(desc);
        findViewById(R.id.btn_reveive).setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        Intent intent=new Intent();
        finish();
    }
}
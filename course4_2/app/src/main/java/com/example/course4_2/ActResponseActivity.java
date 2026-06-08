package com.example.course4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.course4_2.utile.DateUtil;

public class ActResponseActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String mResponse="门口好多人围观，我看到了李晨 、周深、baby";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_response);
        TextView tv_request=findViewById(R.id.tv_request);
        Bundle bundle=getIntent().getExtras();
        String request_time=bundle.getString("request_time");
        String request_content=bundle.getString("request_content");
        String desc=String.format("收到鸭先知请求消息:\n请求时间:%s\n请求内容:%s",request_time,request_content);
        tv_request.setText(desc);
        findViewById(R.id.btn_response).setOnClickListener(this);
        TextView tv_response= findViewById(R.id.tv_response);
        tv_response.setText("美得很待返回的消息:"+mResponse);
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("response_time", DateUtil.getNowTime());
        bundle.putString("response_content",mResponse);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
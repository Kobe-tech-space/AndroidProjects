package com.example.course4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.course4_2.utile.DateUtil;

public class ActRequestActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String mRequest = "跑男来理工大学博物馆了吗？";
    private TextView tv_response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_request);
        TextView tv_request=findViewById(R.id.tv_request);
        tv_request.setText(mRequest);
        findViewById(R.id.btn_request).setOnClickListener(this);
        tv_response = findViewById(R.id.tv_response);
    }
    @Override
    public void onClick(View v){
        Intent intent=new Intent(this,ActResponseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("request_time", DateUtil.getNowTime());
        bundle.putString("request_content",mRequest);
        intent.putExtras(bundle);
        startActivityForResult(intent,0);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent intent) {
        super.onActivityResult(requestCode,resultCode,intent);
        if (intent!=null&&requestCode==0&&resultCode== Activity.RESULT_OK){
            Bundle bundle=intent.getExtras();
            String response_time= bundle.getString("response_time");
            String response_content= bundle.getString("response_content");
            String desc=String.format("收到美得很返回消息:\n应答时间为:%s\n应答内容为:%s",response_time,response_content);
            tv_response.setText(desc);
        }
    }
}
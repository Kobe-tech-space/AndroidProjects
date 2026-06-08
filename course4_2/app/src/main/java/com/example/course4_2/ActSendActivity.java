package com.example.course4_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.example.course4_2.utile.DateUtil;

public class ActSendActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_send);
        tv_send=findViewById(R.id.tv_send);
        findViewById(R.id.btn_send).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,ActReceiveActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("request_time", DateUtil.getNowTime());
        bundle.putString("request_content",tv_send.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
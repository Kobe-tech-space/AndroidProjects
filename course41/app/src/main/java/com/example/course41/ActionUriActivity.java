package com.example.course41;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ActionUriActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_uri);
        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_my).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        String phoneNO = "12345";
        switch (v.getId()) {
            case R.id.btn_dial:
                intent.setAction(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:" + phoneNO);
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.btn_sms:
                intent.setAction(Intent.ACTION_SENDTO);
                Uri uri2 = Uri.parse("smsto:" + phoneNO);
                intent.setData(uri2);
                startActivity(intent);
                break;
            case R.id.btn_my:
                intent.setAction("android.intent.action.YAXIANZHI");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                break;
        }
    }

}
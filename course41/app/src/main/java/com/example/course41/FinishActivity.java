package com.example.course41;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class FinishActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        findViewById(R.id.btn_finish).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_finish || v.getId()==R.id.iv_back) {
            finish();
        }
    }
}
package com.example.exercise4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }
    public void onClick(View v) {
        if(v.getId()==R.id.iv_back) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

}
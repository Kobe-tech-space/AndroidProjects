package com.example.exercise4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_reg).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.tv_forgot_password).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_forgot_password) {
            Intent intent1 = new Intent(this,ForgotPasswordActivity.class);
            intent1.setFlags(intent1.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent1);
        }
        if(v.getId()==R.id.btn_login) {
            Intent intent2 = new Intent(this,LoginSuccessActivity.class);
            intent2.setFlags(intent2.FLAG_ACTIVITY_CLEAR_TASK | intent2.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }
    }

}
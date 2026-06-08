package com.example.exercise5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class LoginForgetActivity extends AppCompatActivity implements View.OnClickListener {
    private String mPhone;
    private String mVerifyCode;
    private EditText et_password_first;
    private EditText et_password_second;
    private EditText et_verifycode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);
        et_password_first=findViewById(R.id.et_password_first);
        et_password_second=findViewById(R.id.et_password_second);
        et_verifycode=findViewById(R.id.et_verifycode);
        et_password_first.addTextChangedListener(new HideTextWatcher(et_password_first,6));
        et_password_second.addTextChangedListener(new HideTextWatcher(et_password_second,6));
        mPhone=getIntent().getStringExtra("phone");
        findViewById(R.id.btn_verifycode).setOnClickListener(this);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String password_first=et_password_first.getText().toString();
        String password_second=et_password_second.getText().toString();
        if(password_first.length()<6) {
            Toast.makeText(this,"请输入正确的密码",Toast.LENGTH_SHORT).show();
            return;
        }
     switch (v.getId()) {
         case R.id.btn_verifycode:
             mVerifyCode=String.format("%06d",new Random().nextInt(999999));
             AlertDialog.Builder builder=new AlertDialog.Builder(this);
             builder.setTitle("请记住验证码");
             builder.setMessage("手机号"+mPhone+",本次验证码是"+mVerifyCode+",请再次输入验证码");
             builder.setPositiveButton("好的",null);
             AlertDialog dialog= builder.create();
             dialog.show();
             break;
         case R.id.btn_confirm:
             if(!password_first.equals(password_second)) {
                 Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                 return;
             }
             if(!et_verifycode.getText().toString().equals(mVerifyCode)) {
                 Toast.makeText(this,"请输入正确的验证码:",Toast.LENGTH_SHORT).show();
                 return;
             }
             Toast.makeText(this,"密码修改成功",Toast.LENGTH_SHORT).show();
             Intent intent=new Intent();
             intent.putExtra("new_password",password_first);
             setResult(Activity.RESULT_OK,intent);
             finish();
             break;
     }
    }
}
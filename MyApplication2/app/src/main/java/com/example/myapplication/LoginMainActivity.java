package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LoginMainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private RadioButton rb_password;
    private RadioButton rb_verifycode;
    private EditText et_phone;
    private EditText et_password;
    private TextView tv_phone;
    private TextView tv_password;
    private String mPassword;
    private String mVerifycode;
    private CheckBox ck_remember;
    private Button btn_login;
    private Button btn_password_forget;
    private RadioGroup rg_login;
    private ActivityResultLauncher<Intent> register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        et_phone=findViewById(R.id.et_phone);
        et_password=findViewById(R.id.et_password);
        tv_phone=findViewById(R.id.tv_phone);
        tv_password=findViewById(R.id.tv_password);
        ck_remember=findViewById(R.id.ck_remember);
        btn_password_forget=findViewById(R.id.btn_login_forget);
        rb_verifycode=findViewById(R.id.rb_verifycode);
        rb_password=findViewById(R.id.rb_password);
        btn_login = findViewById(R.id.btn_login);
        btn_password_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        rg_login=findViewById(R.id.rg_login);
        rg_login.setOnCheckedChangeListener(this);
        register=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent=result.getData();
                if(intent!=null&&result.getResultCode()== Activity.RESULT_OK){
                    mPassword=intent.getStringExtra("new_password");
                }
            }
        });
    }
    @Override
    public void onCheckedChanged(RadioGroup group,int checkedID) {
        switch (checkedID){
            case R.id.rb_password:
                tv_phone.setText("手机号码:");
                tv_password.setText("登陆密码:");
                ck_remember.setVisibility(View.VISIBLE);
                btn_password_forget.setText("忘记密码");
                et_password.setText(null);
                break;
            case R.id.rb_verifycode:
                tv_phone.setText("手机号码:");
                tv_password.setText(" 验证码:");
                ck_remember.setVisibility(View.GONE);
                btn_password_forget.setText("获取验证码");
                et_password.setText(null);
                break;
        }
    }
    @Override
    public void onClick(View v) {
        String phone=et_phone.getText().toString();
        if(phone.length()<11) {
            Toast.makeText(this,"请输入正确的手机号码:",Toast.LENGTH_LONG).show();
            return;
        }
        switch (v.getId()) {
            case R.id.btn_login_forget:
            if(rb_password.isChecked()) {
                    Intent intent=new Intent(this,LoginForgetActivity.class);
                    intent.putExtra("phone",phone);
                    register.launch(intent);
            } else if (rb_verifycode.isChecked()) {
                mVerifycode=String.format("%06d",new Random().nextInt(999999) );
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码");
                builder.setMessage("您的验证码是:"+mVerifycode+",请输入验证码:");
                builder.setPositiveButton("好的",null);
                AlertDialog dialog=builder.create();
                dialog.show();
            }
            break;
            case R.id.btn_login:
                if(rb_password.isChecked()) {
                    if(!mPassword.equals(et_password.getText().toString())) {
                        Toast.makeText(this,"请输入正确的密码",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else if(rb_verifycode.isChecked()) {
                    if(!et_password.getText().toString().equals(mVerifycode)) {
                        Toast.makeText(this,"请输入正确的验证码",Toast.LENGTH_SHORT).show();
                    }
                }
                loginSuccess();
        }
    }
    private void loginSuccess() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("登录成功!");
        builder.setMessage("用户"+et_phone.getText().toString()+"登录成功");
        builder.setPositiveButton("确定",null);
        builder.setNegativeButton("我再看看",null);
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
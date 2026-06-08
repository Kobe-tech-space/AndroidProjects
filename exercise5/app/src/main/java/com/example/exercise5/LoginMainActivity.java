package com.example.exercise5;

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

import com.example.exercise5.HideTextWatcher;

import java.util.Random;

public class LoginMainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private TextView tv_password;
    private EditText et_password;
    private Button btn_forget;
    private CheckBox ck_remember;
    private EditText et_phone;
    private RadioButton rb_password;
    private RadioButton rb_verifycode;
    private ActivityResultLauncher<Intent> register;
    private Button btn_login;
    private String mPassword;
    private String mVerifycode;
    private SharedPreferences preferences;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        prefs=getSharedPreferences("login",MODE_PRIVATE);
        mPassword=prefs.getString("password","111111");
        RadioGroup rb_login=findViewById(R.id.rg_login);
        tv_password=findViewById(R.id.tv_password);
        et_phone=findViewById(R.id.et_phone);
        et_password=findViewById(R.id.et_password);
        btn_forget=findViewById(R.id.btn_forget);
        ck_remember=findViewById(R.id.ck_remember);
        rb_password=findViewById(R.id.rb_password);
        rb_verifycode=findViewById(R.id.rb_verifycode);
        btn_login=findViewById(R.id.btn_login);
        rb_login.setOnCheckedChangeListener(this);
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone,11));
        et_password.addTextChangedListener(new HideTextWatcher(et_password,6));
        btn_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        register=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent=result.getData();
                if(intent!=null&&result.getResultCode()==Activity.RESULT_OK){
                    mPassword=intent.getStringExtra("new_password");
                    SharedPreferences.Editor editor1=prefs.edit();
                    editor1.putString("password",mPassword);
                    editor1.commit();
                    preferences=getSharedPreferences("share_login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("password",mPassword);
                    editor.commit();
                }
            }
        });
        preferences=getSharedPreferences("share_login",MODE_PRIVATE);
        reload();
    }
    @Override
    public void onCheckedChanged(RadioGroup group,int checkedId){
        switch (checkedId){
            case R.id.rb_password:
                tv_password.setText(getString(R.string.login_password));
                et_password.setHint(getString(R.string.input_password));
                btn_forget.setText(getString(R.string.forget_password));
                ck_remember.setVisibility(View.VISIBLE);
                et_password.setText(null);
                break;
            case R.id.rb_verifycode:
                tv_password.setText(getString(R.string.verifycode));
                et_password.setHint(getString(R.string.input_verifycode));
                btn_forget.setText(getString(R.string.get_verifycode));
                ck_remember.setVisibility(View.GONE);
                et_password.setText(null);
                break;
        }
    }
    @Override
    public void onClick(View v) {
        String phone=et_phone.getText().toString();
        if(phone.length()<11){
            Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        switch (v.getId()){
            case R.id.btn_forget:
                if(rb_password.isChecked()) {
                    Intent intent=new Intent(this,LoginForgetActivity.class);
                    intent.putExtra("phone",phone);
                    register.launch(intent);
                } else if (rb_verifycode.isChecked()) {
                    mVerifycode=String.format("%06d",new Random().nextInt(999999));
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("请记住验证码");
                    builder.setMessage("手机号"+phone+",本次验证码是"+mVerifycode+",请输入验证码");
                    builder.setPositiveButton("好的",null);
                    AlertDialog dialog=builder.create();
                    dialog.show();
            }
                break;
            case R.id.btn_login:
                if(rb_password.isChecked()) {
                    if(!mPassword.equals(et_password.getText().toString())){
                        Toast.makeText(this,"请输入正确的密码",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else if(rb_verifycode.isChecked()) {
                    if(!et_password.getText().toString().equals(mVerifycode)) {
                        Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                loginSuccess();
                break;
        }
    }
    private void loginSuccess(){
        String desc=String.format("您的手机号码是%s,恭喜您通过登录验证,点击“确定”按钮返回上个页面",et_phone.getText().toString());
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("登陆成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定",(dialog,which)->{
            finish();
        });
        builder.setNegativeButton("我再看看",null);
        AlertDialog dialog= builder.create();
        dialog.show();

        if(rb_password.isChecked()) {
            SharedPreferences.Editor editor = preferences.edit();
            if(ck_remember.isChecked()) {
                editor.putString("phone", et_phone.getText().toString());
                editor.putString("password", et_password.getText().toString());
                editor.putBoolean("isRemember", true);
            } else {
                editor.putString("phone", et_phone.getText().toString());
                editor.putBoolean("isRemember", false);
            }
            editor.commit();
        }
    }
    private void reload() {
        boolean isRemember =preferences.getBoolean("isRemember",false);
        if(isRemember) {
            String phone=preferences.getString("phone","");
            et_phone.setText(phone);
            String password=preferences.getString("password","");
            et_password.setText(password);
            ck_remember.setChecked(true);
        }
    }
}
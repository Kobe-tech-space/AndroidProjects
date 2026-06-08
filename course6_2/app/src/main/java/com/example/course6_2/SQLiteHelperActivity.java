package com.example.course6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.course6_2.database.UserDBHelper;
import com.example.course6_2.entity.User;
import com.example.course6_2.util.ToastUtil;
import android.util.Log;

import java.util.List;

public class SQLiteHelperActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private EditText et_weight;
    private CheckBox ck_married;
    private UserDBHelper mHelper;
    private TextView tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_helper);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);
        tv_result = findViewById(R.id.tv_result);
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_select).setOnClickListener(this);
    }
    @Override
    protected  void onStart() {
        super.onStart();
        mHelper = UserDBHelper.getInstance(this);
        mHelper.openWriteLink();
        mHelper.openReadLink();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mHelper.closeLink();
    }
    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_height.getText().toString();
        String weight = et_weight.getText().toString();
        User user = null;
        switch (v.getId()) {
            case R.id.btn_save:
                if(name.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty()) {
                    ToastUtil.show(this,"请填写完整信息");
                    return;
                }
                user = new User(name,
                        Integer.parseInt(age),
                        Long.parseLong(height),
                        Float.parseFloat(weight),
                        ck_married.isChecked());
                if (mHelper.insert(user)>0) {
                    ToastUtil.show(this,"添加成功");
                }
                break;
            case R.id.btn_delete:
                if(mHelper.deleteByName(name)>0) {
                    ToastUtil.show(this,"删除成功");
                }
                break;
            case R.id.btn_update:
                if(name.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty()) {
                    ToastUtil.show(this,"请填写完整信息");
                    return;
                }
                user = new User(name,
                        Integer.parseInt(age),
                        Long.parseLong(height),
                        Float.parseFloat(weight),
                        ck_married.isChecked());
                if (mHelper.update(user)>0) {
                    ToastUtil.show(this,"修改成功");
                }
                break;
            case R.id.btn_select:
                 List<User> list = mHelper.queryALL();
                 StringBuilder sb = new StringBuilder();
                //List<User> list = mHelper.queryByname(name);
                for (User u :list) {
                    sb.append(u.toString()).append("\n");
                    Log.d("legion",u.toString());
                }
                tv_result.setText(sb.toString());
                break;
        }
    }
}
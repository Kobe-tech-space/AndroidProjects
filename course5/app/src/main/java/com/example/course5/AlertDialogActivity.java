package com.example.course5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        tv_alert=findViewById(R.id.tv_alert);
        findViewById(R.id.btn_alert).setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("尊敬的用户:");
        builder.setMessage("你真的要卸载我吗?");
        builder.setPositiveButton("残忍卸载",(dialog,which)->{
            tv_alert.setText("虽然依依不舍，但是只能离开了");
        });
        builder.setNegativeButton("我在想想",(dialog,which)->{
            tv_alert.setText("让我再陪你三百六十五天");
        });
        AlertDialog dialog= builder.create();
        dialog.show();
    }
}
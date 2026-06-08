package com.example.course5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener{
    private DatePicker dp_date;
    private TextView tv_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_date).setOnClickListener(this);
        tv_date=findViewById(R.id.tv_date);
        dp_date=findViewById(R.id.dp_date);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_ok:
                String desc=String.format("您选择的日期是%d年%d月%d日",
                        dp_date.getYear(),dp_date.getMonth(),dp_date.getDayOfMonth());
                tv_date.setText(desc);
                break;
            case R.id.btn_date:
                DatePickerDialog dialog= new DatePickerDialog(this,this,
                        2090,5,11);
                dialog.show();
                break;
        }
    }
    @Override
    public void onDateSet(DatePicker view,int year,int month,int dayOfMonth) {
        String desc=String.format("您选择的日期是%s年%s月%s日",year,month+1,dayOfMonth);
        tv_date.setText(desc);
    }
}
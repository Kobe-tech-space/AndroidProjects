package com.example.course5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    private TimePicker tp_time;
    private TextView tv_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        findViewById(R.id.btn_time).setOnClickListener(this);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        tp_time=findViewById(R.id.tp_time);
        tp_time.setIs24HourView(true);
        tv_time=findViewById(R.id.tv_time);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
                String desc = String.format("您选择的时间是%d时%d分", tp_time.getCurrentHour(), tp_time.getCurrentMinute());
                tv_time.setText(desc);
                break;
            case R.id.btn_time:
                Calendar calendar=Calendar.getInstance();
                TimePickerDialog dialog=new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog,this,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
                dialog.show();
                break;
        }
    }
    @Override
    public void onTimeSet(TimePicker view,int hourOfDay,int minute) {
        String desc=String.format("您选择的时间是%d时%d分",hourOfDay,minute);
        tv_time.setText(desc);
    }
}
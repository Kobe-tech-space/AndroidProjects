package com.example.exercise8;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.example.exercise8.adapter.BillPagerAdapter;
import com.example.exercise8.database.BillDBHelper;
import com.example.exercise8.fragment.BillFragment;

import java.util.Calendar;

public class BillPagerActivity extends AppCompatActivity
        implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private TextView tv_month;
    private Calendar calendar;
    private ViewPager vp_bill;
    private BillDBHelper mDBHelper;
    private BillPagerAdapter adapter;  // 保存适配器引用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_pager);

        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_option = findViewById(R.id.tv_option);
        tv_title.setText("账单列表");
        tv_option.setText("添加账单");

        tv_month = findViewById(R.id.tv_month);
        ImageView iv_arrow = findViewById(R.id.iv_arrow);
        iv_arrow.setOnClickListener(this);

        // 显示当前日期
        calendar = Calendar.getInstance();
        tv_month.setText(getMonthString(calendar));

        // 点击弹出日期对话框
        tv_month.setOnClickListener(this);
        tv_option.setOnClickListener(this);
        findViewById(R.id.tv_back).setOnClickListener(this);

        mDBHelper = BillDBHelper.getInstance(this);
        mDBHelper.openReadLink();
        mDBHelper.openWriteLink();

        // 初始化页面视图
        initViewPager();
    }

    // 初始化翻页视图
    private void initViewPager() {
        PagerTabStrip pts_bill = findViewById(R.id.pts_bill);
        pts_bill.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        vp_bill = findViewById(R.id.vp_bill);
        adapter = new BillPagerAdapter(getSupportFragmentManager(),
                calendar.get(Calendar.YEAR));
        vp_bill.setAdapter(adapter);
        vp_bill.setCurrentItem(calendar.get(Calendar.MONTH));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_month:
            case R.id.iv_arrow:
                showDatePickerDialog();
                break;
            case R.id.tv_option:
                Intent intent = new Intent(this, BillAddActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        tv_month.setText(getMonthString(calendar));
        vp_bill.setCurrentItem(month);
    }

    private String getMonthString(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return year + "-" + String.format("%02d", month);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 刷新当前显示的 Fragment
        if (vp_bill != null && adapter != null) {
            int currentPosition = vp_bill.getCurrentItem();
            BillFragment fragment = (BillFragment) adapter.getItem(currentPosition);
            if (fragment != null) {
                String currentMonth = getMonthString(calendar);
                fragment.refreshData(currentMonth);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDBHelper != null) {
            mDBHelper.closeLink();
        }
    }
}
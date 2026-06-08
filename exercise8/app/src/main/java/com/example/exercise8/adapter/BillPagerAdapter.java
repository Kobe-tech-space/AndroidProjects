package com.example.exercise8.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.annotation.Nullable;

import com.example.exercise8.fragment.BillFragment;

public class BillPagerAdapter extends FragmentPagerAdapter {
    private final int mYear;

    public BillPagerAdapter(@NonNull FragmentManager fm, int year) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mYear = year;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int month = position + 1;  // position 0 表示1月，1 表示2月
        // 9 -> 09, 10 -> 10
        String zeroMonth = month < 10 ? "0" + month : String.valueOf(month);
        String yearMonth = mYear + "-" + zeroMonth;
        // 例如：2035-09
        Log.d("legion", yearMonth);
        return BillFragment.newInstance(yearMonth);
    }

    @Override
    public int getCount() {
        return 12;  // 一年12个月
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (position + 1) + "月";  // 显示 "1月"、"2月"... "12月"
    }
}
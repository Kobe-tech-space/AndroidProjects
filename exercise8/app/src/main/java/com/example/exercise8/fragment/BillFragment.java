package com.example.exercise8.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.exercise8.R;
import com.example.exercise8.adapter.BillListAdapter;
import com.example.exercise8.database.BillDBHelper;
import com.example.exercise8.entity.BillInfo;

import java.util.List;

public class BillFragment extends Fragment {

    private BillDBHelper mDBHelper;
    private ListView lv_bill;
    private BillListAdapter adapter;
    private String currentYearMonth;  // ✅ 添加这个成员变量

    public static BillFragment newInstance(String yearMonth) {
        BillFragment fragment = new BillFragment();
        Bundle args = new Bundle();
        args.putString("yearMonth", yearMonth);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        lv_bill = view.findViewById(R.id.lv_bill);

        // 获取数据库帮助器实例
        mDBHelper = BillDBHelper.getInstance(getContext());

        // 获取传入的年月参数
        Bundle arguments = getArguments();
        if (arguments != null) {
            currentYearMonth = arguments.getString("yearMonth");
        }

        // 加载数据
        loadData();

        return view;
    }

    // ✅ 添加 loadData 方法：加载数据
    private void loadData() {
        if (mDBHelper != null && currentYearMonth != null) {
            List<BillInfo> billInfoList = mDBHelper.queryByMonth(currentYearMonth);
            adapter = new BillListAdapter(getContext(), billInfoList);
            lv_bill.setAdapter(adapter);
        }
    }

    // 刷新数据的方法
    public void refreshData(String yearMonth) {
        // 更新成员变量
        this.currentYearMonth = yearMonth;

        // 更新参数
        Bundle args = getArguments();
        if (args != null) {
            args.putString("yearMonth", yearMonth);
        }

        // 重新加载数据
        loadData();
    }
}
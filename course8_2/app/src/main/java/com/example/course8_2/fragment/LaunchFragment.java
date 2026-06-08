package com.example.course8_2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.course8_2.R;
import com.example.course8_2.util.ToastUtil;

public class LaunchFragment extends Fragment {
    public static LaunchFragment newInstance(int count,int position,int image_id) {
        LaunchFragment fragment = new LaunchFragment();
        Bundle args = new Bundle();
        args.putInt("count",count);
        args.putInt("position",position);
        args.putInt("image_id",image_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        Context context = getContext();
        Bundle arguments = getArguments();
        int count = arguments.getInt("count",0);
        int position = arguments.getInt("position",0);
        int imageId = arguments.getInt("image_id",0);
        View view = LayoutInflater.from(context).inflate(R.layout.item_launch,container,
                false);
        ImageView iv_launch = view.findViewById(R.id.iv_launch);
        RadioGroup rg_indicate = view.findViewById(R.id.rg_indicate);
        Button btn_start = view.findViewById(R.id.btn_start);
        iv_launch.setImageResource(imageId);
        for (int j = 0; j<count;j++) {
            RadioButton radio = new RadioButton(context);
            radio.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
            radio.setPadding(10,10,10,10);
            rg_indicate.addView(radio);
        }
        ((RadioButton)rg_indicate.getChildAt(position)).setChecked(true);
        if (position == count -1) {
            btn_start.setVisibility(View.VISIBLE);
            btn_start.setOnClickListener(v -> {
                ToastUtil.show(context,"欢迎您开启美好生活");
            });
        }
        return view;
    }
}

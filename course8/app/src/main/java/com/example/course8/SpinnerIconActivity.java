package com.example.course8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.course8.util.ToastUtil;

public class SpinnerIconActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int[] iconArray = {
            R.drawable.shuixing,R.drawable.jinxing,R.drawable.earth,
            R.drawable.huoxing,R.drawable.muxing,R.drawable.tuxing
    };
    private static final String[] starArray = {"水星","金星","地球","火星","木星","土星"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_icon);
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0;i<iconArray.length;i++) {
            Map<String,Object> item = new HashMap<>();
            item.put("icon",iconArray[i]);
            item.put("name",starArray[i]);
            list.add(item);
        }
        SimpleAdapter startAdapter = new SimpleAdapter(this,list,
                R.layout.item_simple,
                new String[]{"icon","name"},
                new int[]{R.id.iv_icon,R.id.tv_name});
        Spinner sp_icon = findViewById(R.id.sp_icon);
        sp_icon.setPrompt("请选择行星");
        sp_icon.setAdapter(startAdapter);
        sp_icon.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this,"您选择的是" + starArray[position]);
        Log.d("legion","onItemSelected");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {  }
}
package com.example.course8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.course8.util.ToastUtil;

public class SpinnerDropdownActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final static String[] starArray = {"水星","金星","地球","火星","木星","土星"};
    private Spinner sp_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_dropdown);
        sp_dialog = findViewById(R.id.sp_dialog);
        ArrayAdapter<String> starAdapter  = new ArrayAdapter<>(this,R.layout.item_select,starArray);
        sp_dialog.setPrompt("请选择行星");
        sp_dialog.setAdapter(starAdapter);
        sp_dialog.setSelection(0);
        sp_dialog.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this,"您选择的是" + starArray[position]);
        Log.d("legion","onItemSelected");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
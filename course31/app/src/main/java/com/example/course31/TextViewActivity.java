package com.example.course31;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        TextView tv_sp=findViewById(R.id.tv_sp);
        tv_sp.setTextColor(Color.RED);
        TextView tv_dp=findViewById(R.id.tv_dp);
        tv_dp.setTextColor(Color.BLUE);
        TextView tv_code_eight=findViewById(R.id.tv_code_eight);
        tv_code_eight.setTextColor(0xff00ff00);
        TextView tv_code_six=findViewById(R.id.tv_code_six);
        tv_code_six.setTextColor(0x00ff00);
        TextView tv_code_background=findViewById(R.id.tv_code_background);
        tv_code_background.setBackgroundResource(R.color.android);
        TextView tv_code=findViewById(R.id.tv_code);
        ViewGroup.LayoutParams params=tv_code.getLayoutParams();
        params.width=0;
        tv_code.setLayoutParams(params);
        //tv_code.setBackgroundResource(R.color.android);
    }
}
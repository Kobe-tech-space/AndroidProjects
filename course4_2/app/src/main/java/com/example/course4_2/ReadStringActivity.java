package com.example.course4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReadStringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_string);
        TextView tv_resource=findViewById(R.id.tv_resource);
        String value=getString(R.string.message_str);
        tv_resource.setText(value);
    }
}
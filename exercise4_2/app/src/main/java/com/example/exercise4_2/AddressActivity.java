package com.example.exercise4_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AddressActivity extends AppCompatActivity {

    private EditText etCity, etDistrict, etAddress, etName, etPhone, etPostcode;
    private Button btnSave;
    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        // 初始化控件
        etCity = findViewById(R.id.et_city);
        etDistrict = findViewById(R.id.et_district);
        etAddress = findViewById(R.id.et_address);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etPostcode = findViewById(R.id.et_postcode);
        btnSave = findViewById(R.id.btn_save);
        tvDisplay = findViewById(R.id.tv_display);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddress();
            }
        });
    }

    private void saveAddress() {
        String city = etCity.getText().toString();
        String district = etDistrict.getText().toString();
        String address = etAddress.getText().toString();
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String postcode = etPostcode.getText().toString();

        // 格式化显示
        String display = "收货地址：\n" +
                city + "" + district + "\n" +
                address + "\n" +
                name + "\n" +
                phone + "\n" +
                postcode;

        tvDisplay.setText(display);
    }
}
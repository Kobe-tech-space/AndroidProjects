package com.example.course8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.course8.adapter.PlanetBaseAdapter;
import com.example.course8.entity.Planet;

import java.util.List;

import com.example.course8.util.ToastUtil;

public class BaseAdapterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private List<Planet> planetList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);
        Spinner sp_planet = findViewById(R.id.sp_planet);
        planetList = Planet.getDefaultList();
        PlanetBaseAdapter adapter = new PlanetBaseAdapter(this,planetList);
        sp_planet.setPrompt("请选择行星");
        sp_planet.setAdapter(adapter);
        sp_planet.setSelection(0);
        sp_planet.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this,"您选择的行星是" + planetList.get(position).name);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
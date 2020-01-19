package com.example.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intentSensorInfo = new Intent(this, SensorInfo.class);
        ListView lSensor = (ListView)findViewById(R.id.listView_sensor);

        SensorManager sMenager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        final List<Sensor> sensorList = sMenager.getSensorList(Sensor.TYPE_ALL);

        ArrayList<String> sensorName = new ArrayList<String>();
        for (Sensor s : sensorList){
            // pobranie i przypisanie do listy nazwy sensora
            sensorName.add(s.getName());
        }

        ArrayAdapter<String> sensorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sensorName);
        lSensor.setAdapter(sensorAdapter);

        lSensor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intentSensorInfo.putExtra("type", sensorList.get(position).getType());
                startActivity(intentSensorInfo);
            }
        });
    }
}

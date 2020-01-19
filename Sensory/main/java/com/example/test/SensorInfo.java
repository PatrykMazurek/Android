package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class SensorInfo extends AppCompatActivity {

    SensorManager sMenager;
    Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_info);

        ListView lvSensorInfo = (ListView)findViewById(R.id.listView_info_sensor);
        final TextView tvSensorValue = (TextView) findViewById(R.id.textView_measurements_values);
        sMenager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Intent intent = getIntent();
        Toast.makeText(this, intent.getIntExtra("type", 0)+"", Toast.LENGTH_SHORT).show();
        mSensor = sMenager.getDefaultSensor(intent.getIntExtra("type", 0));
        ArrayList<String> sensorInfo = new ArrayList<String>();

        sensorInfo.add("Name: " + mSensor.getName());
        sensorInfo.add("Type: " + mSensor.getStringType());
        sensorInfo.add("Vendor: " + mSensor.getVendor());
        sensorInfo.add("Version: " + mSensor.getVersion());
        sensorInfo.add("Min dalay: " + mSensor.getMinDelay());
        sensorInfo.add("Max delay: " + mSensor.getMaxDelay());
        sensorInfo.add("Range: " + mSensor.getMaximumRange());
        sensorInfo.add("Resolution: " + mSensor.getResolution());
        sensorInfo.add("Reporting mode: " + mSensor.getReportingMode());

        ArrayAdapter<String> sensorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sensorInfo);
        lvSensorInfo.setAdapter(sensorAdapter);

        SensorEventListener sensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                switch (mSensor.getType()){
                    // Environment sensors
                    case Sensor.TYPE_LIGHT:
                        tvSensorValue.setText(String.valueOf(event.values[0]) + "lm");
                        break;
                    case Sensor.TYPE_PRESSURE:
                        tvSensorValue.setText(String.valueOf(event.values[0]) + "hPa");
                        break;
                    case Sensor.TYPE_RELATIVE_HUMIDITY:

                        break;
                    case Sensor.TYPE_AMBIENT_TEMPERATURE:
                        break;

                        //possition sensor
                    case Sensor.TYPE_GAME_ROTATION_VECTOR:
                        float x = (float) (event.values[0] * Math.sin(Math.PI/2));
                        float y = (float) (event.values[1] * Math.sin(Math.PI/2));
                        float z = (float) (event.values[2] * Math.sin(Math.PI/2));

                        tvSensorValue.setText("X: " + String.valueOf(x) + "\n" +
                                "Y: " + String.valueOf(y) + "\n" +
                                "Z: " + String.valueOf(z) + "\n" );
                        break;
                    case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:

                        break;
                    case Sensor.TYPE_MAGNETIC_FIELD:

                        break;
                    case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                        break;
                    case Sensor.TYPE_PROXIMITY:
                        break;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        // rejsetracja nasłuchiwania na sensor
        sMenager.registerListener(sensorListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}

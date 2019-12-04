package com.example.kolokwium;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ConfigurationInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.*;
import java.util.function.IntToDoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView)findViewById(R.id.list);

        ArrayList<HashMap<String, String>> tempList = new ArrayList<>();

        HashMap<String, String> T = new HashMap<>();
        T.put("name1", "Linia 1");
        T.put("name2", "Linia 2");
        tempList.add(T);

        T = new HashMap<>();
        T.put("name1", "Linia 1");
        T.put("name2", "Linia 2");
        tempList.add(T);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, tempList, android.R.layout.simple_expandable_list_item_2,
                new String[] {"name1", "name2"}, new int[] {android.R.id.text1, android.R.id.text2});

        lv.setAdapter(simpleAdapter);
    }


}

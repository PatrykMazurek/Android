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

        final EditText etId = (EditText)findViewById(R.id.editText_index);
        final EditText etName = (EditText)findViewById(R.id.editText_name);
        final EditText etLastname = (EditText)findViewById(R.id.editText_lastname);
        final EditText etcity = (EditText)findViewById(R.id.editText_city);
        Button bSave = (Button)findViewById(R.id.button_save);
        Button bUpdate = (Button)findViewById(R.id.button_update);
        Button bDelete = (Button)findViewById(R.id.button_delete);
        Button bFilter = (Button)findViewById(R.id.button_filter);
        final ListView lvPerson = (ListView)findViewById(R.id.list);

        final DBHepler helper = new DBHepler(this, "person.db", 1);

        final ArrayList<String> personList = helper.getAllPerson();

        final ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, personList);
        lvPerson.setAdapter(arrayAdapter);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String lastname = etLastname.getText().toString();
                String city = etcity.getText().toString();
                long id = helper.insertPerson(name, lastname, city);
                if (id != -1){
                    personList.clear();
                    personList.addAll(helper.getAllPerson());
                    arrayAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Id " + id, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Coś poszło nie tak", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String index = etName.getText().toString();
                String name = etName.getText().toString();
                long update = helper.updatePerson(index, name);
                if (update != -1){
                    personList.clear();
                    personList.addAll(helper.getAllPerson());
                    arrayAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Aktualizacja rekordu " + update, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Coś poszło nie tak", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                long delete = helper.deletePerson(id);
                if (delete != -1){
                    personList.clear();
                    personList.addAll(helper.getAllPerson());
                    arrayAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Usunięto rekordu " + delete, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Coś poszło nie tak", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Proszę pdać Imię do wyszukania", Toast.LENGTH_LONG).show();
                }else{
                    personList.clear();
                    personList.addAll(helper.getPerson(name));
                }
            }
        });

    }


}

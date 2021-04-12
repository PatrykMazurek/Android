package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Environment;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sPref;
    private SharedPreferences.Editor prefEditor;
    ArrayList<String> recordList;
    private EditText etNewRecord;
    private ListView lvRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // reference th the controls 
        etNewRecord = (EditText)findViewById(R.id.et_new_record);
        Button bSave = (Button)findViewById(R.id.b_save);
        Button bClear = (Button)findViewById(R.id.b_clear_data);
        lvRecord = (ListView)findViewById(R.id.lv_record);
        // Inicjalizacja SharedPreferences
        sPref = getSharedPreferences("prefLostData", MODE_PRIVATE);
        prefEditor = sPref.edit();

        recordList = new ArrayList<String>();
        recordList.add("Dowolny rekord");
        final ArrayAdapter<String> recordAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, recordList);
        lvRecord.setAdapter(recordAdapter);

        // Wstawienie do kontrolek EditText danych z SharedPreferences, jeżeli danych niema wstawiane jest 'NULL'
        //etNewRecord.setText(sPref.getString("lost_record", null));
        etNewRecord.setText(sPref.getString("lost_record", null));

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // odczytanie danych z kontrolki EditText i dodanie do listy
                String text = etNewRecord.getText().toString();
                recordList.add(text);
                recordAdapter.notifyDataSetChanged();
            }
        });

        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				// this method clears the data stored in 
                // Czyszczenie zapisanych danych w SharedPreferences i w kontrolkach EditText
                prefEditor.clear();
                prefEditor.apply();
                etNewRecord.setText(null);

            }
        });
    }

    // Metody przy pomocy których można zapisywać/odczytywać dane lub sterować
    // aplikacją w całym cyklu życia aplikacji
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Metoda Start", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Metoda Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Metoda Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Stop method", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        String text = etNewRecord.getText().toString();
        Toast.makeText(this, "Destroy method ", Toast.LENGTH_SHORT).show();
        if(!text.isEmpty()){
            prefEditor.putString("lost_record",etNewRecord.getText().toString());
            prefEditor.apply();
            Toast.makeText(this, "Dane z EditText zapisane", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, etNewRecord.getText().toString(), Toast.LENGTH_SHORT).show();
        }
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Metoda Restart", Toast.LENGTH_SHORT).show();
    }

    // metody zapisujące stan aktywności


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list",recordList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        recordList.addAll(savedInstanceState.getStringArrayList("list"));

    }
}

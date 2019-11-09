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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Odwołanie do kontrolek
        final EditText etName = (EditText)findViewById(R.id.etName);
        final EditText etLastName = (EditText)findViewById(R.id.etLastName);
        final EditText etCity = (EditText)findViewById(R.id.etCity);
        Button bSave = (Button)findViewById(R.id.bSave);
        Button bClear = (Button)findViewById(R.id.bClearData);
        final CheckBox chSaveData = (CheckBox)findViewById(R.id.checkBox);

        // Inicjalizacja SharedPreferences
        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor prefEditor = sPref.edit();

        // Wstawienie do kontrolek EditText danych z SharedPreferences, jeżeli danych niema wstawiane jest 'NULL'
        etName.setText(sPref.getString("Name", null));
        etLastName.setText(sPref.getString("LastName", null));
        etCity.setText((sPref.getString("City", null)));

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(chSaveData.isChecked()){
                    // Zapisywanie do SharedPreferences
                    prefEditor.putString("Name", etName.getText().toString());
                    prefEditor.putString("LastName", etLastName.getText().toString());
                    prefEditor.putString("City", etCity.getText().toString());
                    prefEditor.apply();
                }
                // Tworzenie odwołania do nowej aktywności
                // Wyjaśnienie konstruktora Intent(obecna aktywność, do której aktywności chcemy przejść)
                Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                // Zapisanie danych do wysłania do nowej aktywności
                // Wyjaśnienie intent.putExtra(Klucz, Wartość) w takim formacie wysyłane są dane
                intent.putExtra("Name", etName.getText().toString());
                intent.putExtra("LastName", etLastName.getText().toString());
                intent.putExtra("City", etCity.getText().toString());
                // Otwieranie nowej aktywności bez sprawdzania wyniku/stanu aktywności
                //startActivity(intent);
                // Otwieranie nowej Aktywnosci ze sprwadzaniem wyniku/stanu aktywności
                startActivityForResult(intent, 1);
            }
        });

        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Czyszczenie zapisanych danych w SharedPreferences i w kontrolkach EditText
                prefEditor.clear();
                prefEditor.apply();
                etCity.setText(null);
                etLastName.setText(null);
                etName.setText(null);
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
        Toast.makeText(this, "Metoda Stop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Metoda Destroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Metoda Restart", Toast.LENGTH_SHORT).show();
    }

    // Metoda która sprawdza wynik/stan zamkniętej aktywności i pobiera dane jeżeli zostaly wysłane z zamkniętej aktywności
    @Override
    protected void onActivityResult(int requestCode, int resultsCode, Intent data){
        if(requestCode ==1)
        {
            if(resultsCode == Activity.RESULT_OK){
                Toast.makeText(getApplicationContext(), "Powruconi przez przycisk ' Powrót ' '", Toast.LENGTH_SHORT).show();
                // Wyświetlenie danych które zostały wysłane z zamkniętej aktywności
                Toast.makeText(getApplicationContext(), "Wiadomość z poprzedniej aktywności: " + data.getExtras().get("Message"),Toast.LENGTH_LONG).show();
            }
            if(resultsCode == Activity.RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Anulowano ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

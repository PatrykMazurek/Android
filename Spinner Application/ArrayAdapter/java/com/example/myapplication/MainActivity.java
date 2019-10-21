package com.example.myapplication;

import android.os.Build;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bSave = (Button)findViewById(R.id.b_save);
        final EditText etCountry = (EditText)findViewById(R.id.et_country);
        final Spinner sCountry = (Spinner)findViewById(R.id.s_country);

        // przygotowanie danych
        final ArrayList<String> countryList = new ArrayList<>();

        // przygotowanie list z widoków zapisanych w Androidzie
        countryList.add("Niemcy");
        countryList.add("Polska");
        countryList.add("Hiszpania");

        final ArrayAdapter<String> cArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, countryList);

        sCountry.setAdapter(cArrayAdapter);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = etCountry.getText().toString();
                countryList.add(country);
                etCountry.setText("");

                cArrayAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Dodano państwo do listy", Toast.LENGTH_SHORT).show();
            }
        });

        sCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, countryList.get((int)id)+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}

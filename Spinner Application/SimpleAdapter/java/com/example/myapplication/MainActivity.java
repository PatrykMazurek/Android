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
        final TextView tvText = (TextView)findViewById(R.id.tv_text);
        final EditText etCountry = (EditText)findViewById(R.id.et_country);
        final EditText etCapitol = (EditText)findViewById(R.id.et_capitol);
        final Spinner sCountry = (Spinner)findViewById(R.id.s_country);

        // przygotowanie danych
        final ArrayList<HashMap<String, Object>> countryList = new ArrayList<>();

        // przygotowanie list z widoków zapisanych w Androidzie
        HashMap<String, Object> tempMap = new HashMap<>();
        tempMap.put("country", "Polska");
        tempMap.put("capitol", "Warszawa");
        countryList.add(tempMap);
        tempMap = new HashMap<>();
        tempMap.put("country", "Hiszpania");
        tempMap.put("capitol", "Madryt");
        countryList.add(tempMap);
        // stworzenie adaptera
        final SimpleAdapter cSimpleAdapter = new SimpleAdapter(this, countryList,
                android.R.layout.simple_list_item_2,
                new String[] {"country", "capitol"},
                new int[] {android.R.id.text1, android.R.id.text2});
        // połączenie adaptera z widokiem
        sCountry.setAdapter(cSimpleAdapter);
        // koniec

        // Przygotowanie list opartej na własnych widokach
//        HashMap<String, Object> tempMap = new HashMap<>();
//        tempMap.put("country", "Polska");
//        tempMap.put("capitol", "Warszawa");
//        tempMap.put("flag", R.drawable.flag_of_poland);
//        countryList.add(tempMap);
//        tempMap = new HashMap<>();
//        tempMap.put("country", "Hiszpania");
//        tempMap.put("capitol", "Madryt");
//        tempMap.put("flag", R.drawable.flag_of_spain);
//        countryList.add(tempMap);
//
//        final SimpleAdapter cSimpleAdapter = new SimpleAdapter(this, countryList,
//                R.layout.my_item, new String[] {"country", "capitol", "flag"},
//                new int[] {R.id.tv_country, R.id.tv_capitol, R.id.iv_flag});
//
//        sCountry.setAdapter(cSimpleAdapter);
        // koniec

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = etCountry.getText().toString();
                String capitol = etCapitol.getText().toString();
                HashMap<String, Object> tempMap = new HashMap<>();
                tempMap.put("country", country);
                tempMap.put("capitol", capitol);
                tempMap.put("flag", R.drawable.ic_launcher_background);
                countryList.add(tempMap);
                etCountry.setText("");
                etCapitol.setText("");
                cSimpleAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Dodano państwo do listy", Toast.LENGTH_SHORT).show();
            }
        });

        sCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, countryList.get((int)id).get("country")+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}

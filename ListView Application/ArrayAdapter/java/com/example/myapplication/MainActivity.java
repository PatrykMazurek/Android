package com.example.myapplication;

import android.os.Build;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bSave = (Button)findViewById(R.id.b_save);
        final TextView tvText = (TextView)findViewById(R.id.tv_text);
        final EditText etCountry = (EditText)findViewById(R.id.et_country);
        final ListView lvCountry = (ListView)findViewById(R.id.lv_country);

        final ArrayList<String> countryList = new ArrayList<>();
        countryList.add("Polska");
        countryList.add("Rosja");
        countryList.add("Francja");
        countryList.add("Hiszpania");

        final ArrayAdapter<String> cArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                countryList);
        // definicja zaznaczania elementów z listy przy zastosowaniu odpowiedniego widoku
        //lvCountry.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvCountry.setAdapter(cArrayAdapter);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etCountry.getText().toString();
                countryList.add(text);
                etCountry.setText("");
                cArrayAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Dodano państwo do listy", Toast.LENGTH_SHORT).show();
            }
        });

        lvCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, countryList.get((int)id)+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

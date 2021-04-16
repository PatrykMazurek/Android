package com.masiad.test_4;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Country> countryList;
    CountryAdapter countryAdapter;
    GridView countryGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryGrid = (GridView)findViewById(R.id.grid_view_country);
        Button save = (Button)findViewById(R.id.button_add);

        List<HashMap<String, String>> personListMap = new ArrayList<>();

        countryList = new ArrayList<>();
        List<HashMap<String, Object>> countryListMap = new ArrayList<>();

        countryList.add(new Country("France", "Paris", 643801, R.drawable.flag_of_france));
        countryList.add(new Country("Finland", "Helsinki", 338145, R.drawable.flag_of_finland));

        for (Country country : countryList){
            countryListMap.add(country.ConverToMap()) ;
        }

//        SimpleAdapter countryAdapter = new SimpleAdapter(this, countryListMap, R.layout.list_item,
//                new String[] {"name", "area", "flag"}, new int[] {R.id.country_name, R.id.country_area, R.id.country_flag});

        countryAdapter = new CountryAdapter(this, countryList);
        countryGrid.setAdapter(countryAdapter);
        //        listPerson.setAdapter(countryAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToNextActivity = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intentToNextActivity, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1){
                String name = data.getStringExtra("name");
                String capital = data.getStringExtra("capital");
                int area = data.getIntExtra("area", 0);
                int flag = data.getIntExtra("flag", R.drawable.flag_of_tanzania);
                Country newCountry = new Country(name, capital, area, flag);
                countryList.add(newCountry);
                countryAdapter.notifyDataSetChanged();
                countryGrid.setAdapter(countryAdapter);

                Toast.makeText(this, "back for user", Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "canceled activity", Toast.LENGTH_SHORT).show();
        }
    }
}
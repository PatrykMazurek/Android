package com.masiad.test_4;

import android.view.View;
import android.widget.*;
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

//        final TextView textInfo = (TextView)findViewById(R.id.Text_info);
//        TextView textName = (TextView)findViewById(R.id.textView2);
//        final EditText personName = (EditText)findViewById(R.id.et_name);
//        Button bShow = (Button)findViewById(R.id.b_show);
//        CheckBox genderM = (CheckBox)findViewById(R.id.gender);
//        CheckBox genderF = (CheckBox)findViewById(R.id.gender2);
//        ListView listPerson = (ListView)findViewById(R.id.l_person);
        GridView countryGrid = (GridView)findViewById(R.id.grid_view_country);

        final List<Person> personList = new ArrayList<Person>(){};

        List<HashMap<String, String>> personListMap = new ArrayList<>();

//        personList.add(new Person("Tom", "Kowalski", 30));
//        personList.add(new Person("Jay", "Nowak", 50));
//
//        for (Person person : personList){
//            personListMap.add(person.ConvertToMap());
//        }

//        SimpleAdapter simPersonAdapter = new SimpleAdapter(this, personListMap, android.R.layout.simple_list_item_2,
//                new String[] {"name", "age"}, new int[] {android.R.id.text1, android.R.id.text2});
//        listPerson.setAdapter(simPersonAdapter);

//        final ArrayAdapter<String> personAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
//                personList);
//        listPerson.setAdapter(personAdapter);

        final List<Country> countryList = new ArrayList<>();
        List<HashMap<String, Object>> countryListMap = new ArrayList<>();

        countryList.add(new Country("France", "Paris", 643801, R.drawable.flag_of_france));
        countryList.add(new Country("Finland", "Helsinki", 338145, R.drawable.flag_of_finland));

        for (Country country : countryList){
            countryListMap.add(country.ConverToMap()) ;
        }

//        SimpleAdapter countryAdapter = new SimpleAdapter(this, countryListMap, R.layout.list_item,
//                new String[] {"name", "area", "flag"}, new int[] {R.id.country_name, R.id.country_area, R.id.country_flag});

        CountryAdapter countryAdapter = new CountryAdapter(this, countryList);
        countryGrid.setAdapter(countryAdapter);
        //        listPerson.setAdapter(countryAdapter);
//
//        bShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = String.valueOf(personName.getText());
//                textInfo.setText("Hellow " + name);
////                personList.add(name);
////                personAdapter.notifyDataSetChanged();
//            }
//        });
//
//        genderM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    Toast.makeText(getApplicationContext(), "selected male", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        genderF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    Toast.makeText(getApplicationContext(), "selected female", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        listPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,  "Capitol: "+ countryList.get((int)id).capitol, Toast.LENGTH_SHORT).show();;
//            }
//        });
    }
}
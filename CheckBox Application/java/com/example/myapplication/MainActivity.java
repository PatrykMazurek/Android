package com.example.myapplication;

import android.os.Build;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bSave = (Button)findViewById(R.id.b_save);
        final TextView tvText = (TextView)findViewById(R.id.tv_text);
        // stworzenie listy z odowłaniem do kontrolek checkBox
        final CheckBox[] chVegetableList = {
                (CheckBox)findViewById(R.id.vegetable_1),
                (CheckBox)findViewById(R.id.vegetable_2),
                (CheckBox)findViewById(R.id.vegetable_3),
                (CheckBox)findViewById(R.id.vegetable_4),
                (CheckBox)findViewById(R.id.vegetable_5),
                (CheckBox)findViewById(R.id.vegetable_6)};

        chVegetableList[0].setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ChengeBackgroundColor(chVegetableList[0]);
            }
        });

        chVegetableList[1].setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ChengeBackgroundColor(chVegetableList[1]);
            }
        });

        chVegetableList[2].setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ChengeBackgroundColor(chVegetableList[2]);
            }
        });

        chVegetableList[3].setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ChengeBackgroundColor(chVegetableList[3]);
            }
        });

        chVegetableList[4].setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ChengeBackgroundColor(chVegetableList[4]);
            }
        });

        chVegetableList[5].setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ChengeBackgroundColor(chVegetableList[5]);
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<chVegetableList.length;i++){
                    if(chVegetableList[i].isChecked()){
                        Toast.makeText(MainActivity.this, chVegetableList[i].getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void ChengeBackgroundColor(CheckBox box){
        if(box.isChecked()){
            box.setBackgroundColor(getResources().getColor(R.color.checked, null) );
        }else{
            box.setBackgroundColor(getResources().getColor(R.color.not_checked, null));
        }
    }

}

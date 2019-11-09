package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        // ustawienie stałej orientacji okranu można zmienić w AndroidManifest.XML
        // dopisując do Activity parametr android:screenOrientation

        TextView tvOldRecord = (TextView)findViewById(R.id.tv_old_record);
        TextView tvNewRecord = (TextView)findViewById(R.id.tv_new_record);
        TextView tvRotation = (TextView)findViewById(R.id.tv_rotation);
        Button bPrev = (Button)findViewById(R.id.bPrev);

        tvRotation.setText(getString(R.string.rotation));

        // Przykłady obrania danych wysłanych z poprzedniej aktywności do obecnej
        Intent intent = getIntent();

        //odczytanie danych z SharedPreferences jeżeli zostały zapisane
        SharedPreferences sPref = getSharedPreferences("MyPref",MODE_PRIVATE);
        // odczytanie danych i zapisanie ich do kontrolki textView
        tvNewRecord.setText(getString(R.string.new_record)+":\n" +
                intent.getExtras().getString("Name") + "\n"+
                intent.getExtras().getString("LastName") + "\n"+
                intent.getExtras().getString("City"));
        //odczytanie danych z SharedPreferences i zapisanie w kontrolce TextView
        HashMap<String, String> tempMap = (HashMap<String, String>) sPref.getAll();
        if(tempMap.isEmpty()){
            tvOldRecord.setText(getString(R.string.old_record) + ":\n" +
                    getString(R.string.empty));
        }else{
            tvOldRecord.setText(getString(R.string.old_record) + ":\n" +
                    tempMap.get("Name") + "\n" + tempMap.get("LastName") + "\n" +
                    tempMap.get("City"));
        }

        bPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Wymagane aby zwrócić wynik/stan zamykanej aktywności
                Intent backintent = new Intent();
                // Opcjonalnie można przesłać dane do poprzedniej aktywności
                backintent.putExtra("Message", "Przekazana wiadomość powrotna");
                // Nadanie wyniku/sanu zamykanej aktywności który będzie sprawdzany w MainActivity.java
                setResult(Activity.RESULT_OK, backintent);
                // Zamykanie obecnej aktywności i powrót do poprzedniej aktywności
                finish();
            }
        });
    }
}

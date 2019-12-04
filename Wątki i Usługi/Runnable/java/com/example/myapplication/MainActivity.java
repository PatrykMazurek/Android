package com.example.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // odwołanie od kontrolek z widoku
        final ProgressBar pRunable = (ProgressBar)findViewById(R.id.progressBar_runable);
        final TextView tInfo = (TextView)findViewById(R.id.textView_info);
        final EditText etTime = (EditText)findViewById(R.id.editText_time_r);
        Button bRunable = (Button)findViewById(R.id.button_runable);

        // akcja wywołana po przyciśnięciu przycisku
        bRunable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pobranie wartości z kontrolki EditText
                final int max = (etTime.getText().toString().isEmpty()) ? 0 : Integer.parseInt(etTime.getText().toString());
                // ustawienie maksymalnej wrtości kontrolki ProgressBar
                pRunable.setMax(max);
                // incjalizacja nowego wątka
                Thread test = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i <= max; i++){
                            final int number = i;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            tInfo.post(new Runnable() {
                                @Override
                                public void run() {
                                    tInfo.setText(getResources().getText(R.string.number) +": " + number + " / " + max );
                                }
                            });
                            pRunable.setProgress(i);
                        }
                    }
                });
                test.start();
            }
        });
    }
}

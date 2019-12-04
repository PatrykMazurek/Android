package com.example.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvMessage;
    Intent intentService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMessage = (TextView)findViewById(R.id.textView);
        Button bStartService = (Button)findViewById(R.id.button_start);
        Button bStopService = (Button)findViewById(R.id.button_stop);

        bStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentService = new Intent(MainActivity.this, MyService.class);
                intentService.putExtra("text", "losowa liczba");

                startService(intentService);
            }
        });

        bStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intentService);
                intentService = null;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(testReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("com.example.student.l1_service.MyService");

        LocalBroadcastManager.getInstance(this).registerReceiver(testReceiver, filter);
    }

    BroadcastReceiver testReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = "losowa liczba" + intent.getExtras().getInt("number");
            tvMessage.setText(text);
            //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    };
}
package com.example.kolokwium;

import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;

import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lvJson = (ListView)findViewById(R.id.JSON_List);
        Button bNewPost = (Button)findViewById(R.id.b_New);
        final Intent postIntent = new Intent(MainActivity.this, Post.class);
        JSONAsyncTask jsonTest = new JSONAsyncTask(this);
        jsonTest.execute("read");
        //jsonTest.execute("update", "7", "Patryk", "Nowy wpis");
        bNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postIntent.putExtra("new_record", true);
                startActivity(postIntent);
            }
        });

        lvJson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object text = parent.getItemAtPosition(position);
                HashMap<String, String> tempMap = (HashMap<String, String>) text;
                postIntent.putExtra("new_record", false);
                postIntent.putExtra("title", tempMap.get("title"));
                postIntent.putExtra("post", tempMap.get("post"));
                startActivity(postIntent);
            }
        });

    }
}


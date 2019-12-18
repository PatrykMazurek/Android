package com.example.kolokwium;

import android.content.Intent;
import android.icu.util.LocaleData;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        TextView tvDataAdded = (TextView)findViewById(R.id.textView_data_daded);
        TextView tvDataUpdate = (TextView)findViewById(R.id.textView_data_update);
        final EditText etNick = (EditText)findViewById(R.id.editText_nick);
        final EditText etText = (EditText) findViewById(R.id.editText_post);
        Button bSave = (Button)findViewById(R.id.button);
        final Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Intent intent = getIntent();
        final int id;
        if (intent.getExtras().getBoolean("new_record")){
            id = 0;
            bSave.setText("Dodaj nowy post");
            tvDataAdded.setText("Dodano: ----");
            tvDataUpdate.setText("Aktualizacja: " + df.format(date) );
        }else {
            Bundle bundle = intent.getExtras();
            String[] tempText = bundle.getString("title").split(" ");
            id = Integer.parseInt(tempText[0].substring(1,tempText[0].length()-1));
            String nick = "";
            for (int i = 1; i < tempText.length-1; i++){
                nick += tempText[i] + " ";
            }
            tvDataAdded.setText("Dodano: " + tempText[tempText.length-1]);
            tvDataUpdate.setText("Aktualizacja: " + df.format(date));
            etNick.setText(nick);
            etText.setText(bundle.getString("post"));
            bSave.setText("Aktualizuj post");
        }

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick = etNick.getText().toString();
                String text = etText.getText().toString();

                JSONAsyncTask jsonAsyncTask = new JSONAsyncTask(Post.this);
                if (id == 0){
                    jsonAsyncTask.execute("add",nick, text);
                }else{
                    jsonAsyncTask.execute("update", Integer.toString(id), nick, text);
                }
                finish();
            }
        });

    }
}

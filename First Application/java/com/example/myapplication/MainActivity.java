package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etText = (EditText)findViewById(R.id.et_text);
        Button bSave = (Button)findViewById(R.id.b_save);
        final TextView tvText = (TextView)findViewById(R.id.tv_text);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etText.getText().toString();
                tvText.setText(text);
                Toast.makeText(MainActivity.this, "Pobrano tekst", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SaveText(View view) {
        EditText etFromOnClick = (EditText)findViewById(R.id.et_text);
        TextView tvFromOnClick = (TextView)findViewById(R.id.tv_text);
        String text = etFromOnClick.getText().toString();
        tvFromOnClick.setText(text);
        Toast.makeText(this, "Zapisano tekst przy wykorzystaniu metody onClick", Toast.LENGTH_LONG).show();
    }
}

package com.masiad.test_4;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText countryName = (EditText)findViewById(R.id.et_country_name);
        final EditText countryArea = (EditText)findViewById(R.id.et_country_area);
        final EditText countryCapital = (EditText)findViewById(R.id.et_country_capital);
        final ImageView countryFlag = (ImageView)findViewById(R.id.iv_country_flag);
        Button save = (Button)findViewById(R.id.button_save_data);


        countryFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int SELECT_IMAGES = 1;
                Intent setFlag = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(setFlag, SELECT_IMAGES);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = countryName.getText().toString();

                int area = Integer.parseInt(countryArea.getText().toString());
                String capitialName = countryCapital.getText().toString();

//                Country newCountry = new Country(name,capitialName,area,R.drawable.flag_of_tanzania);

                Intent backIntent = new Intent();
                backIntent.putExtra("name", name);
                backIntent.putExtra("area", area);
                backIntent.putExtra("capital", capitialName);
                backIntent.putExtra("flag", R.drawable.flag_of_tanzania);
                setResult(Activity.RESULT_OK, backIntent);
                finish();
            }
        });

    }
}
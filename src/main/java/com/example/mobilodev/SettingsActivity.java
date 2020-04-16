package com.example.mobilodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    CheckBox darkTheme;
    EditText usernameEt, ageEt, weightEt, heightEt, genderEt;
    Button saveB;
    String currentUsername;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        darkTheme = findViewById(R.id.darkThemeCb);
        usernameEt = (EditText)findViewById(R.id.username);
        ageEt = (EditText)findViewById(R.id.age);
        weightEt = (EditText)findViewById(R.id.weight);
        heightEt = (EditText)findViewById(R.id.height);
        genderEt = (EditText)findViewById(R.id.gender);
        saveB = (Button)findViewById(R.id.saveButton);

        currentUsername = MainActivity.getCurrentUsername();

        sharedPreferences = getSharedPreferences("Prefs", MODE_PRIVATE);

        usernameEt.setText(sharedPreferences.getString("id" + currentUsername, currentUsername));
        ageEt.setText(sharedPreferences.getString("age"+ currentUsername, "None"));
        weightEt.setText(sharedPreferences.getString("weight"+ currentUsername, "None"));
        heightEt.setText(sharedPreferences.getString("height"+ currentUsername, "None"));
        genderEt.setText(sharedPreferences.getString("gender"+ currentUsername, "None"));
        darkTheme.setChecked(sharedPreferences.getBoolean("darkTheme"+ currentUsername, false));


        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username, age, weight, height, gender;
                Boolean darkThemeCheckBox;

                username = usernameEt.getText().toString();
                age = ageEt.getText().toString();
                weight = weightEt.getText().toString();
                height = heightEt.getText().toString();
                gender = genderEt.getText().toString();
                darkThemeCheckBox = darkTheme.isChecked();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("id"+ currentUsername, currentUsername);
                editor.putString("age"+ currentUsername, age);
                editor.putString("weight"+ currentUsername, weight);
                editor.putString("height"+ currentUsername, height);
                editor.putString("gender"+ currentUsername, gender);
                editor.putBoolean("darkTheme"+ currentUsername, darkThemeCheckBox);
                editor.commit();
                Toast.makeText(SettingsActivity.this, "Saved", Toast.LENGTH_LONG).show();

            }
        });

    }

}

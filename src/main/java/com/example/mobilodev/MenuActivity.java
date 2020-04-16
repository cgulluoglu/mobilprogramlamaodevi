package com.example.mobilodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button emailB, listB, noteB, sensorB, settingsB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        emailB = (Button)findViewById(R.id.email);
        listB = (Button)findViewById(R.id.list);
        noteB = (Button)findViewById(R.id.note);
        settingsB = (Button)findViewById(R.id.settings);
        sensorB = (Button)findViewById(R.id.sensors);


        emailB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), EmailActivity.class);
                startActivity(i);
            }
        });

        listB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ListActivity.class);
                startActivity(i);
            }
        });

        noteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), NoteActivity.class);
                startActivity(i);
            }
        });

        settingsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SettingsActivity.class);
                startActivity(i);
            }
        });

        sensorB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SensorActivity.class);
                startActivity(i);
            }
        });


    }


}

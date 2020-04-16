package com.example.mobilodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<User> users;
    private static String currentUsername;
    int attemptCounter = 0;
    Button b1;
    EditText username,password;
    TextView remainingTryTv;


    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static ArrayList<User> getUsers() { return users;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.loginButton);
        username = (EditText)findViewById(R.id.message);
        password = (EditText)findViewById(R.id.passwordText);
        remainingTryTv = (TextView)findViewById(R.id.remainingTry);

        users = new ArrayList<>();

        User us = new User("cgulluoglu", "123456");
        us.setImg(R.drawable.cgulluoglu);
        users.add(us);

        us = new User("cemyilmaz", "191919");
        us.setImg(R.drawable.cemyilmaz);
        users.add(us);

        us = new User("elonmusk", "tesla");
        us.setImg(R.drawable.elonmusk);
        users.add(us);

        us = new User("billgates", "applestop");
        us.setImg(R.drawable.billgates);
        users.add(us);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameEntry = username.getText().toString();
                String pwEntry = password.getText().toString();

                attemptCounter++;

                for( User user: users) {
                    if( usernameEntry.equals( user.getUsername()) && pwEntry.equals(user.getPassword())){
                        currentUsername = user.getUsername();
                        Intent i = new Intent(view.getContext(), MenuActivity.class);
                        attemptCounter = 0;
                        startActivity(i);
                    }
                }
                if (attemptCounter == 3) {
                    Toast.makeText(getApplicationContext(), " 3 Wrong attempts, exiting app", Toast.LENGTH_SHORT).show();
                    finish();
                }

                remainingTryTv.setText("Remaining : " + (3 - attemptCounter));
            }
        });
    }
}

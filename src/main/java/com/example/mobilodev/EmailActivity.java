package com.example.mobilodev;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URISyntaxException;

public class EmailActivity extends AppCompatActivity {

    EditText editTextEmail, editTextSubject, editTextMessage;
    Button sendButton, attachmentButton;
    String email, subject, message;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        editTextEmail = (EditText)findViewById(R.id.toWho);
        editTextSubject = (EditText)findViewById(R.id.title);
        editTextMessage = (EditText)findViewById(R.id.message);
        attachmentButton = (Button)findViewById(R.id.attachmentBtn);
        sendButton = (Button)findViewById(R.id.sendBtn);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        attachmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
        }
    }

    public void sendEmail(){
        try {
            email = editTextEmail.getText().toString();
            subject = editTextSubject.getText().toString();
            message = editTextMessage.getText().toString();
            final Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

            if( URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            this.startActivity(Intent.createChooser(emailIntent,"sending mail"));
        } catch (Throwable t) {
            Toast.makeText(this, "failed" , Toast.LENGTH_SHORT).show();
        }
    }

    public void openFolder() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Select a file to attach"), PICK_FROM_GALLERY);


    }



}

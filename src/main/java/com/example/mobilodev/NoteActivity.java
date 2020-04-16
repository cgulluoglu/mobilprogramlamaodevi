package com.example.mobilodev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";

    ListView listView;
    EditText mEditText, fileNameEditText;
    Button saveB, deleteB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mEditText = findViewById(R.id.edit_text);
        fileNameEditText = findViewById(R.id.fileNameEt);

        listView = (ListView)findViewById(R.id.listView);
        saveB = (Button)findViewById(R.id.button_save);
        deleteB = (Button)findViewById(R.id.button_delete);

        final ArrayList<String> fileNames = new ArrayList<>();

        File root = new File(getFilesDir().toString());
        File files[] = root.listFiles();
        for( File file : files) {
            fileNames.add(file.getName());
        }

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fileNames);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                loadFile(fileNames.get(i));
                fileNameEditText.setText(fileNames.get(i));
            }
        });

        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mEditText.getText().toString();
                FileOutputStream fos = null;
                String FileName;
                FileName = fileNameEditText.getText().toString();
                try {
                    fos = openFileOutput(FileName, MODE_PRIVATE);
                    fos.write(text.getBytes());

                    mEditText.getText().clear();

                    fileNames.add(FileName);
                    arrayAdapter.notifyDataSetChanged();

                    Toast.makeText(NoteActivity.this, "Saved to " + getFilesDir() + "/" + FileName,
                            Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FileName = fileNameEditText.getText().toString();
                File fileToDelete = new File(getFilesDir().toString() + "/" + FileName);
                fileToDelete.delete();
                fileNames.remove(FileName);
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }

    public void loadFile(String fileName) {
        FileInputStream fis = null;

        try {
            fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            mEditText.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
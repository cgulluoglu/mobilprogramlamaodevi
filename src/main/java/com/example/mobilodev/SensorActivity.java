package com.example.mobilodev;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{

    private TextView xText, yText, zText, lightSensorTv,value;
    private Sensor ivmeSensor, isikSensor;
    private SensorManager SM;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

        ivmeSensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        isikSensor = SM.getDefaultSensor(Sensor.TYPE_LIGHT);

        SM.registerListener(this, ivmeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        SM.registerListener(this, isikSensor, SensorManager.SENSOR_DELAY_NORMAL);

        xText = (TextView)findViewById(R.id.textViewX);
        yText = (TextView)findViewById(R.id.textViewY);
        zText = (TextView)findViewById(R.id.textViewZ);
        start = (Button)findViewById(R.id.timerStart);
        lightSensorTv = (TextView)findViewById(R.id.lightSensorTv);
        value = (TextView)findViewById(R.id.value);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(5000, 1000) {
                    int counter = 0;

                    @Override
                    public void onTick(long l) {
                        float x = Float.parseFloat(xText.getText().toString());
                        float y = Float.parseFloat(yText.getText().toString());
                        float z = Float.parseFloat(zText.getText().toString());
                        if( ((-0.5 < x && x < 0.5) && ( z < 10.5 && z > 9.5) && (-0.5 < y && y < 0.5)) || ((-0.5 < x && x < 0.5) && (-0.5 < y && y < 0.5) && ( z > -10.5 && z < -9.5))){
                            counter++;
                        }
                    }
                    @Override
                    public void onFinish() {
                        if (counter >= 4) {
                            Toast.makeText(SensorActivity.this, "Cihaz 5sn hareketsiz kaldigi icin kapatiliyor", Toast.LENGTH_LONG).show();
                            finishAffinity();
                        }
                    }
                }.start();

            }
        });
    }

    @Override
    public void onSensorChanged(final SensorEvent sensorEvent) {
        if( sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            xText.setText(Float.toString(sensorEvent.values[0]));
            yText.setText(Float.toString(sensorEvent.values[1]));
            zText.setText(Float.toString(sensorEvent.values[2]));

        } else if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {

            lightSensorTv.setText("" + sensorEvent.values[0]);
            if(sensorEvent.values[0] < 5) {
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                xText.setTextColor(Color.WHITE);
                yText.setTextColor(Color.WHITE);
                zText.setTextColor(Color.WHITE);
                value.setTextColor(Color.WHITE);
                lightSensorTv.setTextColor(Color.WHITE);
            } else {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                xText.setTextColor(Color.BLACK);
                yText.setTextColor(Color.BLACK);
                zText.setTextColor(Color.BLACK);
                value.setTextColor(Color.BLACK);
                lightSensorTv.setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Not in use
    }

}

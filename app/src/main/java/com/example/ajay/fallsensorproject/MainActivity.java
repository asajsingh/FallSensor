package com.example.ajay.fallsensorproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity implements SensorEventListener,OnClickListener{

    private SensorManager sensorManager;
    private Sensor mySensor;
    private TextView x;
    private TextView y;
    private TextView z;
    private Button btnStart, btnStop;
    private LinearLayout layout;
    private boolean started = false;
    // private View mChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        //sensorData = new ArrayList();
        layout = (LinearLayout) findViewById(R.id.linearLayout);
        // mySensor=SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        x=(TextView) findViewById(R.id.textView2);
        y=(TextView) findViewById(R.id.textView3);
        z=(TextView) findViewById(R.id.textView4);

    }
    protected void onResume() {
        super.onResume();
        //SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }



    //onPause() unregister the accelerometer for stop listening the events

    protected void onPause() {

        super.onPause();
        sensorManager.unregisterListener(this);
    }
    ArrayList<Float> xa = new ArrayList<Float>();
    ArrayList<Float> ya= new ArrayList<Float>();
    ArrayList<Float> za= new ArrayList<Float>();
    ArrayList<Long> t= new ArrayList<Long>();

    @Override
    public void onAccuracyChanged(Sensor sensor,int accuracy) {
    }
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(started) {
            x.setText("X : " + event.values[0]);
            y.setText("Y : " + event.values[1]);
            z.setText("Z : " + event.values[2]);
            long timestamp = System.currentTimeMillis();
            xa.add(event.values[0]);
            ya.add(event.values[1]);
            za.add(event.values[2]);
            t.add(timestamp);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                started = true;
                mySensor = sensorManager
                        .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(this, mySensor,
                        SensorManager.SENSOR_DELAY_FASTEST);
                break;
            case R.id.btnStop:
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                started = false;
                sensorManager.unregisterListener(this);


            default:
                break;
        }
    }





}


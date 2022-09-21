package com.group18.activityclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import java.io.InputStream;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.SerializationHelper;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private MySensor mySensor;
    private SensorEventListener sensorEventListener;

    private final static String FILE_J48 = "j48tree.model";
    private final static String FILE_MP = "mp.model";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        init();

        loadWekaModel();
    }

    private void loadWekaModel() {
        try (InputStream stream = getAssets().open(FILE_J48)) {
            J48 tree = (J48) SerializationHelper.read(stream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        mySensor = new MySensor();
        SensorEventListener sensorListener = new SensorActivity(mySensor);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), 500);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 500);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), 500);
    }

}
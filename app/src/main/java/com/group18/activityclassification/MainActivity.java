package com.group18.activityclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Switch;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.SerializationHelper;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private MySensor mySensor;
    private SensorEventListener sensorEventListener;

    private final static String FILE_J48 = "j48tree.model";
    private final static String FILE_MP = "mp.model";

    Queue<String> queue = new LinkedList<String>();
    final int queueLength = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        init();
    }

    private void init() {
        loadWekaModel();

        mySensor = new MySensor();
        SensorEventListener sensorListener = new SensorActivity(mySensor);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), 500);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 500);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), 500);
    }

    private void loadWekaModel() {
        try (InputStream stream = getAssets().open(FILE_J48)) {
            J48 tree = (J48) SerializationHelper.read(stream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initQueue(){
        for(int i = 0; i < queueLength; i++){
            queue.add("0/");
        }
    }

    private void addQueue(String activity){
        queue.remove();
        queue.add(activity);
    }

    private String tallyQueue(){
        int[] tallyArray = {0, 0, 0, 0, 0, 0, 0};
        Queue<String> tallyQueue = queue;
        for(int i = 0; i < queueLength; i++){
            String type = tallyQueue.poll();
            switch(type){
                case "walking":
                    tallyArray[0]++;
                    break;
                case "standing":
                    tallyArray[1]++;
                    break;
                case "jogging":
                    tallyArray[2]++;
                    break;
                case "sitting":
                    tallyArray[3]++;
                    break;
                case "biking":
                    tallyArray[4]++;
                    break;
                case "upstairs":
                    tallyArray[5]++;
                    break;
                case "downstairs":
                    tallyArray[6]++;
                    break;
                default:
                    break;
            }
        }

        int indexMax = 0;
        for(int i = 0; i < 7; i++){
            indexMax = tallyArray[i] > tallyArray[indexMax] ? i : indexMax;
        }

        String activity = "\0";
        switch(indexMax){
            case 0:
                activity = "walking";
                break;
            case 1:
                activity = "standing";
                break;
            case 2:
                activity = "jogging";
                break;
            case 3:
                activity = "sitting";
                break;
            case 4:
                activity = "biking";
                break;
            case 5:
                activity = "upstairs";
                break;
            case 6:
                activity = "downstairs";
                break;
            default:
                break;
        }
        return activity;
    }

}
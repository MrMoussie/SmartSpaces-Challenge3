package com.group18.activityclassification;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.ArrayList;

public class SensorActivity implements SensorEventListener {
    ArrayList<Float> Acc = new ArrayList<Float>();
    ArrayList<Float> Lin = new ArrayList<Float>();
    ArrayList<Float> Mag = new ArrayList<Float>();
    ArrayList<Float> Gyro = new ArrayList<Float>();
    com.group18.activityclassification.Sensor sensor;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        switch(sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                Acc = new ArrayList<Float>();
                for(int i = 0; i < 3; i++) {
                    Acc.add(sensorEvent.values[i]);
                }
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                Lin = new ArrayList<Float>();
                for(int i = 0; i < 3; i++) {
                    Lin.add(sensorEvent.values[i]);
                }
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                Mag = new ArrayList<Float>();
                for(int i = 0; i < 3; i++) {
                    Mag.add(sensorEvent.values[i]);
                }
                break;
            case Sensor.TYPE_GYROSCOPE:
                Gyro = new ArrayList<Float>();
                for(int i = 0; i < 3; i++) {
                    Gyro.add(sensorEvent.values[i]);
                }
                break;
            default:
                System.out.println("Type of sensor used, was incorrect.");
        }
        this.sensor = new com.group18.activityclassification.Sensor(Acc, Lin, Gyro, Mag, sensorEvent.timestamp);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

package com.group18.activityclassification;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class SensorActivity implements SensorEventListener {
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        switch(sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                break;
            case Sensor.TYPE_GYROSCOPE:
                break;
            default:
                System.out.println("Type of sensor used, was incorrect.");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

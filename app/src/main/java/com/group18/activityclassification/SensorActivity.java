package com.group18.activityclassification;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.ArrayList;

public class SensorActivity implements SensorEventListener {
    ArrayList<Float> Acc;
    ArrayList<Float> Lin;
    ArrayList<Float> Mag;
    ArrayList<Float> Gyro;
    MySensor sensor;

    public SensorActivity(MySensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        switch(sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                Acc = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    Acc.add(sensorEvent.values[i]);
                    System.out.println("Acc val " + sensorEvent.values[i]);
                }
                this.sensor.setAcc(Acc);
                this.sensor.setTimestamp(sensorEvent.timestamp);
                break;

            case Sensor.TYPE_LINEAR_ACCELERATION:
                Lin = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    Lin.add(sensorEvent.values[i]);
                    System.out.println("Lin val " + sensorEvent.values[i]);
                }
                this.sensor.setLinearAcc(Lin);
                this.sensor.setTimestamp(sensorEvent.timestamp);
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                Mag = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    Mag.add(sensorEvent.values[i]);
                    System.out.println("Mag val " + sensorEvent.values[i]);
                }
                this.sensor.setMagneto(Mag);
                this.sensor.setTimestamp(sensorEvent.timestamp);
                break;

            case Sensor.TYPE_GYROSCOPE:
                Gyro = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    Gyro.add(sensorEvent.values[i]);
                    System.out.println("Gyro val " + sensorEvent.values[i]);
                }
                this.sensor.setGyro(Gyro);
                this.sensor.setTimestamp(sensorEvent.timestamp);
                break;
            default:
                System.out.println("Type of sensor used, was incorrect.");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

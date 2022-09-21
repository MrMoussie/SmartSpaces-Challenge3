package com.group18.activityclassification;

import java.util.ArrayList;

/**
 * Object class for storing sensor values received from the sensors inside the mobile device
 */
public class Sensor {
    private ArrayList<Float> Acc = new ArrayList<>();
    private ArrayList<Float> LinearAcc = new ArrayList<>();
    private ArrayList<Float> Gyro = new ArrayList<>();
    private ArrayList<Float> Magneto = new ArrayList<>();
    private long timestamp;

    public ArrayList<Float> getAcc() {
        return Acc;
    }

    public void setAcc(ArrayList<Float> acc) {
        Acc = acc;
    }

    public ArrayList<Float> getLinearAcc() {
        return LinearAcc;
    }

    public void setLinearAcc(ArrayList<Float> linearAcc) {
        LinearAcc = linearAcc;
    }

    public ArrayList<Float> getGyro() {
        return Gyro;
    }

    public void setGyro(ArrayList<Float> gyro) {
        Gyro = gyro;
    }

    public ArrayList<Float> getMagneto() {
        return Magneto;
    }

    public void setMagneto(ArrayList<Float> magneto) {
        Magneto = magneto;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Sensor(ArrayList<Float> A, ArrayList<Float> L, ArrayList<Float> G, ArrayList<Float> M, long timestamp) {
        this.Acc = A;
        this.LinearAcc = L;
        this.Gyro = G;
        this.Magneto = M;
        this.timestamp = timestamp;
    }

}

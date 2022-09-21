package com.group18.activityclassification;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import weka.classifiers.trees.J48;
import weka.core.SerializationHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadWekaModel();
    }

    private void loadWekaModel() {
        try {
            J48 tree = (J48) SerializationHelper.read("assets/J48.model");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
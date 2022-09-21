package com.group18.activityclassification;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.SerializationHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadWekaModel();
    }

    private void loadWekaModel() {
        try (InputStream stream = getAssets().open("mp.model")) {
            MultilayerPerceptron tree = (MultilayerPerceptron) SerializationHelper.read(stream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
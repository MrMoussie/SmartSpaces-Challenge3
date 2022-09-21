package com.group18.activityclassification;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.SerializationHelper;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_J48 = "j48tree.model";
    private final static String FILE_MP = "mp.model";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadWekaModel();
    }

    private void loadWekaModel() {
        try (InputStream stream = getAssets().open(FILE_J48)) {
            J48 tree = (J48) SerializationHelper.read(stream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
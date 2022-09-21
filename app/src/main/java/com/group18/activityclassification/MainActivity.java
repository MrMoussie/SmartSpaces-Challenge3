package com.group18.activityclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private MySensor mySensor;

    private final static String FILE_J48 = "j48tree.model";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        init();
    }

    private void init() {
        loadWekaModel();

        // Setup sensors
        mySensor = new MySensor();
        SensorEventListener sensorListener = new SensorActivity(mySensor);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), 500);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 500);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), 500);
    }

    private void loadWekaModel() {
        try (InputStream stream = getAssets().open(FILE_J48)) {

            // Attributes for the prediction model
            final Attribute attributeRightPocketAx = new Attribute(Attributes.RIGHT_POCKET_AX.toString());
            final Attribute attributeRightPocketAy = new Attribute(Attributes.RIGHT_POCKET_AY.toString());
            final Attribute attributeRightPocketAz = new Attribute(Attributes.RIGHT_POCKET_AZ.toString());
            final Attribute attributeRightPocketLx = new Attribute(Attributes.RIGHT_POCKET_LX.toString());
            final Attribute attributeRightPocketLy = new Attribute(Attributes.RIGHT_POCKET_LY.toString());
            final Attribute attributeRightPocketLz = new Attribute(Attributes.RIGHT_POCKET_LZ.toString());
            final Attribute attributeRightPocketGx = new Attribute(Attributes.RIGHT_POCKET_GX.toString());
            final Attribute attributeRightPocketGy = new Attribute(Attributes.RIGHT_POCKET_GY.toString());
            final Attribute attributeRightPocketGz = new Attribute(Attributes.RIGHT_POCKET_GZ.toString());
            final List<String> classes = new ArrayList<String>() {
                {
                    add(Attributes.WALKING.toString());
                    add(Attributes.STANDING.toString());
                    add(Attributes.JOGGING.toString());
                    add(Attributes.SITTING.toString());
                    add(Attributes.BIKING.toString());
                    add(Attributes.UPSTAIRS.toString());
                    add(Attributes.DOWNSTAIRS.toString());
                }
            };

            // Instances(...) requires ArrayList<> instead of List<>...
            ArrayList<Attribute> attributeList = new ArrayList<Attribute>(2) {
                {
                    add(attributeRightPocketAx);
                    add(attributeRightPocketAy);
                    add(attributeRightPocketAz);
                    add(attributeRightPocketLx);
                    add(attributeRightPocketLy);
                    add(attributeRightPocketLz);
                    add(attributeRightPocketGx);
                    add(attributeRightPocketGy);
                    add(attributeRightPocketGz);
                    Attribute attributeClass = new Attribute("@@class@@", classes);
                    add(attributeClass);
                }
            };
            // unpredicted data sets (reference to sample structure for new instances)
            Instances dataUnpredicted = new Instances("TestInstances",
                    attributeList, 1);
            // last feature is target variable
            dataUnpredicted.setClassIndex(dataUnpredicted.numAttributes() - 1);

            // TEST INSTANCE
            DenseInstance testInstance = new DenseInstance(dataUnpredicted.numAttributes()) {
                {
                    setValue(attributeRightPocketAx, 0);
                    setValue(attributeRightPocketAy, -9.8);
                    setValue(attributeRightPocketAz, 0);
                    setValue(attributeRightPocketLx, 0);
                    setValue(attributeRightPocketLy, 0);
                    setValue(attributeRightPocketLz, 0);
                    setValue(attributeRightPocketGx, 0);
                    setValue(attributeRightPocketGy, 0);
                    setValue(attributeRightPocketGz, 0);
                }
            };

            // instance to use in prediction
            // reference to dataset
            testInstance.setDataset(dataUnpredicted);

            // import ready trained model
            Classifier cls = (Classifier) weka.core.SerializationHelper
                    .read(stream);

            if (cls == null)
                throw new Exception("Classifier is null!");

            // predict new sample
            try {
                double result = cls.classifyInstance(testInstance);
                System.out.println("Index of predicted class label: " + result + ", which corresponds to class: " + classes.get(Double.valueOf(result).intValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
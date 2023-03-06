package com.example.finalapp03.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalapp03.R;

public class FragSensor extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView lightTextView;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_light_sensor, container, false);
        lightTextView = rootView.findViewById(R.id.light_text_view);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        String lightSituation;
        if (lux < 100) {
            lightSituation = "Oscuro";
        } else if (lux >= 100 && lux <= 2000) {
            lightSituation = "Normal";
        } else {
            lightSituation = "Brillante";
        }
        lightTextView.setText("Situación lumínica: " + lightSituation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No se utiliza en este ejemplo
    }
}
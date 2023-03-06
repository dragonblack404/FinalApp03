package com.example.finalapp03.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalapp03.R;

import java.util.Locale;

public class FragLocation extends Fragment implements LocationListener {

    private LocationManager locationManager;
    private TextView latitudeTextView, longitudeTextView, altitudeTextView, bearingTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        latitudeTextView = view.findViewById(R.id.latitude_text_view);
        longitudeTextView = view.findViewById(R.id.longitude_text_view);
        altitudeTextView = view.findViewById(R.id.altitude_text_view);
        bearingTextView = view.findViewById(R.id.bearing_text_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (lastKnownLocation != null) {
                updateLocationData(lastKnownLocation);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        updateLocationData(location);
    }

    private void updateLocationData(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double altitude = location.getAltitude();
        float bearing = location.getBearing();

        latitudeTextView.setText(String.format(Locale.getDefault(), "Latitude: %.6f", latitude));
        longitudeTextView.setText(String.format(Locale.getDefault(), "Longitude: %.6f", longitude));
        altitudeTextView.setText(String.format(Locale.getDefault(), "Altitude: %.2f", altitude));
        bearingTextView.setText(String.format(Locale.getDefault(), "Bearing: %.2f", bearing));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}
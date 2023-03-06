package com.example.finalapp03.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalapp03.R;

public class FragBateria extends Fragment {

    private TextView mBatteryStatusTextView;
    private TextView mBatteryLevelTextView;
    private BroadcastReceiver mBatteryBroadcastReceiver;

    public FragBateria(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battery, container, false);

        mBatteryStatusTextView = view.findViewById(R.id.battery_status_text_view);
        mBatteryLevelTextView = view.findViewById(R.id.battery_level_text_view);

        // Register a broadcast receiver to receive battery status updates
        mBatteryBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

                // Calculate battery percentage
                float batteryPct = level / (float)scale * 100;

                // Set battery status text
                String batteryStatus;
                switch (status) {
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        batteryStatus = "Charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        batteryStatus = "Discharging";
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        batteryStatus = "Full";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        batteryStatus = "Not charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    default:
                        batteryStatus = "Unknown";
                        break;
                }
                mBatteryStatusTextView.setText("Status: " + batteryStatus);

                // Set battery level text
                mBatteryLevelTextView.setText("Level: " + batteryPct + "%");
            }
        };
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        getActivity().registerReceiver(mBatteryBroadcastReceiver, filter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unregister the broadcast receiver when the fragment is destroyed
        getActivity().unregisterReceiver(mBatteryBroadcastReceiver);
    }
}
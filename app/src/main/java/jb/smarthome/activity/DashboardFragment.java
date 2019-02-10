package jb.smarthome.activity;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.adapter.GridViewAdapter;
import jb.smarthome.api.service.SensorService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements AdapterView.OnItemClickListener {

    GridView androidGridView;
    Context mContext;
    String[] gridViewString = {
            "Alarm",
            "Kamera",
            "Temperatura",
            "Czujniki",
            "Światło",
            "Ustawienia"

    };
    int[] gridViewImageId = {
            R.drawable.ic_baseline_notifications_active_24px,
            R.drawable.ic_baseline_photo_camera_24px,
            R.drawable.ic_baseline_thermometer,
            R.drawable.ic_baseline_wifi_24px,
            R.drawable.ic_baseline_lightbulb,
            R.drawable.ic_baseline_settings_20px

    };
    String[] gridViewSmallText = {
            "Sterowanie alarmem",
            "Sterowanie kamerą",
            "i WILGOTNOŚĆ POWIETRZA",
            "Obserwacja zagrożeń",
            "Sterowanie światłem",
            "Konfiguruj urządzenia"
    };




    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        GridViewAdapter adapterViewAndroid = new GridViewAdapter(getContext(), gridViewString, gridViewImageId, gridViewSmallText);
        androidGridView = (GridView) view.findViewById(R.id.gridview);
        androidGridView.setAdapter(adapterViewAndroid);




        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), AlarmActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), CameraActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), TemperatureActivity.class));
                        break;
                    case 3:
                        Intent sensorIntent = new Intent(getActivity(), SensorActivity.class);
                        startActivity(sensorIntent);
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), LightActivity.class));
                        break;
                    case 5:
                        Toast.makeText(getContext(), "Niedostępne w obecnej wersji", Toast.LENGTH_SHORT).show();

                        break;
                }

            }
        });


        return view;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

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
            "WYŁĄCZONY",
            "WYŁĄCZONA",
            "i WILGOTNOŚĆ POWIETRZA",
            "11 AKTYWNYCH",
            "3/5 WŁĄCZONYCH",
            "5 DOSTĘPNYCH USTAWIEŃ"
    };
    String gasResponse = "0";
    String fireResponse = "0";
    Thread sensorThread;

    Date date;
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    String formattedDate;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(user.getUid()).child("Powiadomienia");
    //DatabaseReference userNotify = myRef.child(user.getUid());
    Map notify = new HashMap();

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

        sensorThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    gasSensor();
                    if(Boolean.parseBoolean(gasResponse) ){

                        date = Calendar.getInstance().getTime();
                        formattedDate = df.format(date);
                        notify.put(formattedDate, new ArrayList<String>(Arrays.asList("Wykryto GAZ","warning")));
                        myRef.updateChildren(notify);
                       /* pushNotification();
                        try {
                            Thread.sleep(15000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        sensorThread.start();


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
                        //startActivity(new Intent(getActivity(), SettingsActivity.class));
                        break;
                }

            }
        });


        return view;
    }

    private void gasSensor() {
        SensorService service = RetrofitClientInstance.getRetrofitInstance().create(SensorService.class);
        Call<String> call = service.gasSensor();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                gasResponse = response.body();

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                gasResponse = "0";
            }
        });
    }
    private void fireSensor() {
        SensorService service = RetrofitClientInstance.getRetrofitInstance().create(SensorService.class);
        Call<String> call = service.fireSensor();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                fireResponse = response.body();

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                fireResponse = "0";
            }
        });
    }
    private void pushNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.mipmap.czujniki)
                .setContentTitle("Tytul")
                .setContentText("Wykryto GAZ!");

        Intent notificationIntent = new Intent(getActivity(),DashboardFragment.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(),0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

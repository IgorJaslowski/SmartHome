package jb.smarthome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.adapter.LightAdapter;
import jb.smarthome.adapter.SensorAdapter;
import jb.smarthome.api.model.Sensor;
import jb.smarthome.api.service.SensorService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SensorActivity extends AppCompatActivity {


    ListView listView;
    String gasResponse = "0";
    String fireResponse = "0";
    boolean motionResponse = false;
    Thread sensorThread;
    @BindView(R.id.partDisconnectedSensor)
    LinearLayout partDisconnectedSensor;
    @BindView(R.id.sensorContent)
    FrameLayout sensorContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        listView = findViewById(R.id.sensorListView);
        ButterKnife.bind(this);



        ArrayList<Sensor> sensorArrayList = new ArrayList<>();
        Sensor sensor1 = new Sensor("Czujnik ognia", Boolean.parseBoolean(fireResponse));
        Sensor sensor2 = new Sensor("Czujnik gazu", Boolean.parseBoolean(gasResponse));
        Sensor sensor3 = new Sensor("Czujnik ruchu", motionResponse);


        sensorArrayList.add(sensor1);
        sensorArrayList.add(sensor2);
        sensorArrayList.add(sensor3);


        try{
            SensorAdapter adapter = new SensorAdapter(this, R.layout.adapter_sensor_view_layout, sensorArrayList);
            listView.setAdapter(adapter);
        }catch (NullPointerException ne){
            ne.printStackTrace();
        }

        sensorThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    gasSensor();
                    fireSensor();
                    motionSensor();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        sensorThread.start();

    }

    private void gasSensor() {
        SensorService service = RetrofitClientInstance.getRetrofitInstance().create(SensorService.class);
        Call<String> call = service.gasSensor();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                gasResponse = response.body();
                partDisconnectedSensor.setVisibility(View.GONE);
                sensorContent.setVisibility(View.VISIBLE);

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                gasResponse = "0";
                sensorContent.setVisibility(View.GONE);
                partDisconnectedSensor.setVisibility(View.VISIBLE);
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
    private void motionSensor() {
        SensorService service = RetrofitClientInstance.getRetrofitInstance().create(SensorService.class);
        Call<Boolean> call = service.motionSensor();
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                motionResponse = response.body();

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                motionResponse = false;
            }
        });
    }
}

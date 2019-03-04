package jb.smarthome.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.adapter.TemperatureAdapter;
import jb.smarthome.api.model.Temperature;
import jb.smarthome.api.model.TemperatureResponse;
import jb.smarthome.api.service.TemperatureService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TemperatureActivity extends AppCompatActivity {

    @BindView(R.id.temperatureAvg)
    TextView tempAvgTextView;
    @BindView(R.id.humidityAvg)
    TextView humidityTextView;
    @BindView(R.id.temperatureListView)
    ListView tempListView;
    private volatile boolean stop = false;
    Thread t1;
    @BindView(R.id.temperatureContent)
    FrameLayout temperatureContent;


    @Override
    public void onDestroy() {
        stop = true;
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        stop = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        ButterKnife.bind(this);


        t1 = new Thread() {
            @Override
            public void run() {
                while (!stop) {


                    TemperatureService service = RetrofitClientInstance.getRetrofitInstance().create(TemperatureService.class);
                    Call<TemperatureResponse> call = service.getTemperatureAndHumidity();
                    call.enqueue(new Callback<TemperatureResponse>() {
                        @Override
                        public void onResponse(Call<TemperatureResponse> call, Response<TemperatureResponse> response) {
                            tempAvgTextView.setText(response.body().getAvgTemperature() + getString(R.string.degreesC));
                            humidityTextView.setText(response.body().getAvgHumidity());
                            ArrayList<Temperature> temperaturesList = new ArrayList<>();
                            temperaturesList = response.body().getTemperatures();
                            TemperatureAdapter adapter = new TemperatureAdapter(getBaseContext(), R.layout.adapter_temperature_view_layout, temperaturesList);
                            tempListView.setAdapter(adapter);
                            System.out.println("Pobrano temperature");
                            temperatureContent.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailure(Call<TemperatureResponse> call, Throwable t) {
                            temperatureContent.setVisibility(View.GONE);
                        }

                    });
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();


    }

}

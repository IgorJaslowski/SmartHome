package jb.smarthome.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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

    TextView textView;
    TextView tempAvgTextView;
    TextView humidityTextView;
    ListView tempListView;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);


        tempAvgTextView = findViewById(R.id.temperatureAvg);



        humidityTextView = findViewById(R.id.humidityAvg);



        tempListView = findViewById(R.id.temperatureListView);



        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    TemperatureService service = RetrofitClientInstance.getRetrofitInstance().create(TemperatureService.class);
                    Call<TemperatureResponse> call = service.getTemperatureAndHumidity();
                    call.enqueue(new Callback<TemperatureResponse>() {
                        @Override
                        public void onResponse(Call<TemperatureResponse> call, Response<TemperatureResponse> response) {
                            tempAvgTextView.setText(response.body().getAvgTemperature());
                            humidityTextView.setText(response.body().getAvgHumidity());
                            ArrayList<Temperature> temperaturesList = new ArrayList<>();
                            temperaturesList = response.body().getTemperatures();
                            TemperatureAdapter adapter = new TemperatureAdapter(getBaseContext(),R.layout.adapter_temperature_view_layout,temperaturesList);
                            tempListView.setAdapter(adapter);

                        }

                        @Override
                        public void onFailure(Call<TemperatureResponse> call, Throwable t) {
                            Toast.makeText(TemperatureActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        t1.start();

        /*Create handle for the RetrofitInstance interface*/




    }

    private void generateText(Float text){
        textView = findViewById(R.id.temperatureAvg);
//        textView.setText("Temperatura wynosi :"+text);

    }
}

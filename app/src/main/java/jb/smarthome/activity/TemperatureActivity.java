package jb.smarthome.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.Temperature;
import jb.smarthome.adapter.TemperatureAdapter;
import jb.smarthome.service.TemperatureService;
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


        tempAvgTextView.setText(23+getString(R.string.degreesC));

        humidityTextView = findViewById(R.id.humidityAvg);

        humidityTextView.setText("52");

        tempListView = findViewById(R.id.temperatureListView);
        Temperature tempSalon = new Temperature("Salon","24,6 "+getString(R.string.degreesC),"54,6");
        Temperature tempKuchnia = new Temperature("Kuchnia","23,2"+getString(R.string.degreesC),"48,9");
        Temperature tempPokoj = new Temperature("Pokój","22,7"+getString(R.string.degreesC),"55,1");
        Temperature tempNew = new Temperature("--","--"+getString(R.string.degreesC),"--");
        ArrayList<Temperature> temperaturesList = new ArrayList<>();
        temperaturesList.add(tempSalon);
        temperaturesList.add(tempKuchnia);
        temperaturesList.add(tempPokoj);
        temperaturesList.add(tempNew);

        TemperatureAdapter adapter = new TemperatureAdapter(this,R.layout.adapter_temperature_view_layout,temperaturesList);
        tempListView.setAdapter(adapter);
        /*Create handle for the RetrofitInstance interface*/

        final TemperatureService service = RetrofitClientInstance.getRetrofitInstance().create(TemperatureService.class);
        Call<Float> call = service.getTemp();
        call.enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                generateText(response.body());
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                Toast.makeText(TemperatureActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void generateText(Float text){
        textView = findViewById(R.id.temperatureAvg);
//        textView.setText("Temperatura wynosi :"+text);

    }
}

package jb.smarthome.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.service.TemperatureService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemperatureActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);




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
        textView = findViewById(R.id.temperatureTextView);
        textView.setText("Temperatura wynosi :"+text);

    }
}

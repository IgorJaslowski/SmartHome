package jb.smarthome.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.adapter.TodoAdapter;
import jb.smarthome.service.LightService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LightActivity extends AppCompatActivity {

    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);


        /*Create handle for the RetrofitInstance interface*/
        final LightService service = RetrofitClientInstance.getRetrofitInstance().create(LightService.class);
        Call<Boolean> call = service.getState();
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                generateText(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(LightActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        Button onLightButton = (Button) findViewById(R.id.onLightButton);

        onLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> call = service.turnOnLed();
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(LightActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Button offLightButton = (Button) findViewById(R.id.offLightButton);
        offLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> call = service.turnOffLed();
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(LightActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    private void generateText(Boolean text){
        textView = findViewById(R.id.isLightOnTextView);
        textView.setText(Boolean.toString(text));

    }
}

package jb.smarthome.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.adapter.TemperatureAdapter;
import jb.smarthome.api.model.Temperature;
import jb.smarthome.api.model.TemperatureResponse;
import jb.smarthome.api.service.CameraService;
import jb.smarthome.api.service.TemperatureService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraActivity extends AppCompatActivity {
    private VideoView streamView;
    private MediaController mediaController;
    private int servoPos;


    @BindView(R.id.servoLeftButton)
    Button servoLeftButton;
    @BindView(R.id.servoCenterButton)
    Button servoCenterButton;
    @BindView(R.id.servoRightButton)
    Button servoRightButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        streamView = (VideoView) findViewById(R.id.videoView);


    }


    @OnClick(R.id.servoLeftButton)
     void servoLeftBtnClick() {

        CameraService service = RetrofitClientInstance.getRetrofitInstance().create(CameraService.class);
        Call<Integer> call = service.turnLeft();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                servoPos = response.body().intValue();
                toggleButton();

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(CameraActivity.this, "Brak połączenia z serwerem.", Toast.LENGTH_SHORT).show();

            }

        });

    }

    @OnClick(R.id.servoCenterButton)
    void servoCenterBtnClick() {

        CameraService service = RetrofitClientInstance.getRetrofitInstance().create(CameraService.class);
        Call<Void> call = service.centerServo();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
    toggleButton();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CameraActivity.this, "Brak połączenia z serwerem.", Toast.LENGTH_SHORT).show();

            }

        });

    }

    @OnClick(R.id.servoRightButton)
    void servoRightBtnClick() {

        CameraService service = RetrofitClientInstance.getRetrofitInstance().create(CameraService.class);
        Call<Integer> call = service.turnRight();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                servoPos = response.body().intValue();
                toggleButton();

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(CameraActivity.this, "Brak połączenia z serwerem.", Toast.LENGTH_SHORT).show();

            }

        });

    }
private void toggleButton(){
    final int MIN_POSITION = 60;
    final int MAX_POSITION = 220;
        if(servoPos<=MIN_POSITION){
            servoRightButton.setEnabled(false);
        }
        else if(servoPos>=MAX_POSITION){
            servoLeftButton.setEnabled(false);
    }
    else{
            servoRightButton.setEnabled(true);
            servoLeftButton.setEnabled(true);
        }
}

    private void playStream() {
        Uri UriSrc = Uri.parse("192.168.43.48:8090");
        if (UriSrc == null) {
            Toast.makeText(CameraActivity.this,
                    "UriSrc == null", Toast.LENGTH_LONG).show();
        } else {
            streamView.setVideoURI(UriSrc);
            mediaController = new MediaController(this);
            streamView.setMediaController(mediaController);
            streamView.start();


        }
    }
}

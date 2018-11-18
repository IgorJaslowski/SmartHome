package jb.smarthome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.api.service.CameraService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CameraActivity extends AppCompatActivity {
    private MediaController mediaController;
    private int servoPos;


    @BindView(R.id.servoLeftButton)
    Button servoLeftButton;
    @BindView(R.id.servoCenterButton)
    Button servoCenterButton;
    @BindView(R.id.servoRightButton)
    Button servoRightButton;
    @BindView(R.id.streamView)
    WebView streamView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        int default_zoom_level=100;
        final String URL = "http://192.168.43.48:8080/stream";
        streamView.setInitialScale(default_zoom_level);
        streamView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return (motionEvent.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        streamView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Get the width and height of the view because its different for different phone or table layouts
        // Pass these values to the URL in the web view to display the HTTP stream
        streamView.post(new Runnable()
        {

            @Override
            public void run() {
                int width = streamView.getWidth();
                int height = streamView.getHeight();
                streamView.loadUrl(URL + "?width="+width+"&height="+height);

            }
        });


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


}

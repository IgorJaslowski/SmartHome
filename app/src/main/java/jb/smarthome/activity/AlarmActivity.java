package jb.smarthome.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.api.service.AlarmService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmActivity extends AppCompatActivity {


    @BindView(R.id.btnAlarmOn)
    Button btnAlarmOn;
    @BindView(R.id.btnAlarmOff)
    Button btnAlarmOff;
    @BindView(R.id.btnAlarm)
    Button btnAlarm;
    @BindView(R.id.partDisconnectedAlarm)
    LinearLayout disconnectedLayout;
    @BindView(R.id.alarmControlLay)
    LinearLayout alarmControlLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);


        final AlarmService service = RetrofitClientInstance.getRetrofitInstance().create(AlarmService.class);
        Call<Boolean> call = service.alarmStatus();
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                disconnectedLayout.setVisibility(View.GONE);
                alarmControlLay.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                disconnectedLayout.setVisibility(View.VISIBLE);
                alarmControlLay.setVisibility(View.GONE);
            }
        });
    }


    @OnClick(R.id.btnAlarmOn)
    void turnAlarmOn() {
        final AlarmService service = RetrofitClientInstance.getRetrofitInstance().create(AlarmService.class);
        Call<Void> call = service.alarmOn();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
    @OnClick(R.id.btnAlarmOff)
    void turnAlarmOff() {
        final AlarmService service = RetrofitClientInstance.getRetrofitInstance().create(AlarmService.class);
        Call<Void> call = service.alarmOff();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
    @OnClick(R.id.btnAlarm)
    void alarmNow() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Czy napewno chcesz uruchomić alarm?")
                .setCancelable(false)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AlarmService service = RetrofitClientInstance.getRetrofitInstance().create(AlarmService.class);
                        Call<Void> call = service.alarm();
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                            }
                        });
                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}






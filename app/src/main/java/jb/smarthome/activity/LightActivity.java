package jb.smarthome.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import jb.smarthome.api.model.Light;
import jb.smarthome.R;
import jb.smarthome.adapter.LightAdapter;

public class LightActivity extends AppCompatActivity {

    TextView howMuchIsOn;
    Switch turnAllOn;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        howMuchIsOn = findViewById(R.id.getAllLight);
        turnAllOn = findViewById(R.id.turnAllOnOff);
        listView = findViewById(R.id.lightListView);

        howMuchIsOn.setText("3/4");

        ArrayList<Light> lightArrayList = new ArrayList<>();
        Light kitchen = new Light("KUCHNIA",true);
        Light room1 = new Light("POKÓJ 1",false);
        Light room2 = new Light("POKÓJ 2",true);
        Light salon = new Light("SALON",true);

        lightArrayList.add(kitchen);
        lightArrayList.add(room1);
        lightArrayList.add(room2);
        lightArrayList.add(salon);

        LightAdapter adapter = new LightAdapter(this, R.layout.adapter_light_view_layout,lightArrayList);
        listView.setAdapter(adapter);

        /*Create handle for the RetrofitInstance interface*/

       /*
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
        });*/
    }


    private void generateText(Boolean text){
//        textView = findViewById(R.id.isLightOnTextView);
//        textView.setText(Boolean.toString(text));

    }
}

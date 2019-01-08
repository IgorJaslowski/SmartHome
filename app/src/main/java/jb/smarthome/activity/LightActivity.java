package jb.smarthome.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.api.model.Light;
import jb.smarthome.R;
import jb.smarthome.adapter.LightAdapter;
import jb.smarthome.api.model.LightResponse;
import jb.smarthome.api.service.LightService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LightActivity extends AppCompatActivity {

    TextView howMuchIsOn;
   // Switch turnAllOn;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        howMuchIsOn = findViewById(R.id.getAllLight);
        //TODO: Zamienić na 2 buttony
       // turnAllOn = findViewById(R.id.turnAllOnOff);
        listView = findViewById(R.id.lightListView);

        howMuchIsOn.setText("3/4");


        final LightService service = RetrofitClientInstance.getRetrofitInstance().create(LightService.class);
        Call<LightResponse> call = service.getState();
        call.enqueue(new Callback<LightResponse>() {

            @Override
            public void onResponse(Call<LightResponse> call, Response<LightResponse> response) {
                ArrayList<Light> lightArrayList = response.body().getLightList();
                System.out.println(response.body().toString());
                LightAdapter adapter = new LightAdapter(getBaseContext(), R.layout.adapter_light_view_layout, lightArrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<LightResponse> call, Throwable t) {
                Toast.makeText(LightActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }

        });


    }

}

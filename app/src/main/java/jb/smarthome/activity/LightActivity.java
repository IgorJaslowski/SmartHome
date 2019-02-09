package jb.smarthome.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.getAllLight)
    TextView howMuchIsOn;
    @BindView(R.id.lightListView)
    ListView listView;
    @BindView(R.id.btnLightTurnAllOn)
    Button btnLightTurnAllOn;
    @BindView(R.id.btnLightTurnAllOff)
    Button btnLightTurnAllOff;
    @BindView(R.id.part)
    LinearLayout linearLayout;
    @BindView(R.id.lightContent)
    FrameLayout lightContent;

    Date date;
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    String formattedDate;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(user.getUid()).child("Powiadomienia");
    //DatabaseReference userNotify = myRef.child(user.getUid());
    Map notify = new HashMap();
    ArrayList arrayList = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("USER: " + user.getEmail());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        ButterKnife.bind(this);


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
                linearLayout.setVisibility(View.GONE);
                lightContent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<LightResponse> call, Throwable t) {
                Toast.makeText(LightActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                lightContent.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);

            }

        });

    }

    @OnClick(R.id.btnLightTurnAllOn)
    void turnAllOn() {
        final LightService service = RetrofitClientInstance.getRetrofitInstance().create(LightService.class);
        Call<Void> call = service.turnAllOn();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
        date = Calendar.getInstance().getTime();
        formattedDate = df.format(date);

        notify.put(formattedDate, new ArrayList<String>(Arrays.asList("Zapalono wszystkie światła","warning")));
        myRef.updateChildren(notify);

    }

    @OnClick(R.id.btnLightTurnAllOff)
    void turnAllOff() {
        final LightService service = RetrofitClientInstance.getRetrofitInstance().create(LightService.class);
        Call<Void> call = service.turnAllOff();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
        date = Calendar.getInstance().getTime();
        formattedDate = df.format(date);
        notify.put(formattedDate,new ArrayList<String>(Arrays.asList("Zgaszono wszystkie światła","information")));
        myRef.updateChildren(notify);
    }

}

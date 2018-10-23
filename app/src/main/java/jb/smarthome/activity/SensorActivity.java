package jb.smarthome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import jb.smarthome.R;
import jb.smarthome.adapter.LightAdapter;
import jb.smarthome.adapter.SensorAdapter;
import jb.smarthome.api.model.Sensor;

public class SensorActivity extends AppCompatActivity {


    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);


        listView = findViewById(R.id.sensorListView);



        ArrayList<Sensor> sensorArrayList = new ArrayList<>();
        Sensor sensor1 = new Sensor("Czujnik ruchu", true);
        Sensor sensor2 = new Sensor("Czujnik ruchu", true);
        Sensor sensor3 = new Sensor("Czujnik ruchu", true);


        sensorArrayList.add(sensor1);
        sensorArrayList.add(sensor2);
        sensorArrayList.add(sensor3);

        System.out.println(sensorArrayList.toString());
        try{
            SensorAdapter adapter = new SensorAdapter(this, R.layout.adapter_sensor_view_layout, sensorArrayList);
            listView.setAdapter(adapter);
        }catch (NullPointerException ne){
            ne.printStackTrace();
        }



    }
}

package jb.smarthome.activity;


import android.content.Context;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import butterknife.ButterKnife;
import jb.smarthome.R;
import jb.smarthome.adapter.GridViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements AdapterView.OnItemClickListener {

    GridView androidGridView;

    String[] gridViewString = {
            "Alarm",
            "Kamera",
            "Temperatura",
            "Czujniki",
            "Światło",
            "Ustawienia"

    };
    int[] gridViewImageId = {
            R.drawable.ic_icon_alarm,
            R.drawable.ic_icon_cam,
            R.drawable.ic_icon_termometr,
            R.drawable.ic_icon_wifi,
            R.drawable.ic_icon_zarowka,
            R.drawable.ic_icon_ustawienia

    };
    String[] gridViewSmallText ={
            "WYŁĄCZONY",
            "WYŁĄCZONA",
            "i WILGOTNOŚĆ POWIETRZA",
            "11 AKTYWNYCH",
            "3/5 WŁĄCZONYCH",
            "5 DOSTĘPNYCH USTAWIEŃ"
    };

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        GridViewAdapter adapterViewAndroid = new GridViewAdapter(getContext(), gridViewString, gridViewImageId,gridViewSmallText);
        androidGridView = (GridView) view.findViewById(R.id.gridview);
        androidGridView.setAdapter(adapterViewAndroid);



        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), AlarmActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), CameraActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), TemperatureActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), SensorActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), LightActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), BassBoost.Settings.class));
                        break;
                }

            }
        });


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

package jb.smarthome.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import jb.smarthome.R;
import jb.smarthome.api.model.Sensor;


public class SensorAdapter extends ArrayAdapter<Sensor> {

    private Context mContext;
    int mResource;


    public SensorAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Sensor> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the temperature information
        String name = getItem(position).getName();
        boolean detection = getItem(position).getDetection();


        //Create the object with the data
        Sensor sensor = new Sensor(name, detection);

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(mResource, parent, false);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.sensorNameTextView);
        TextView detectionTextView = (TextView) convertView.findViewById(R.id.sensorDetectionTextView);
        detectionTextView.setTypeface(null, Typeface.BOLD);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.sensorIconImageView);
        if (position == 0) {
            imageView.setImageResource(R.drawable.ic_fire_icons_for_windows_10_17);
        } else if (position == 1) {
            imageView.setImageResource(R.drawable.ic_gas_icons_for_windows_10_17);
        } else if (position == 2) {
            imageView.setImageResource(R.drawable.ic_motion_icons_for_windows_10_17);
        }


        nameTextView.setText(name);
        detectionTextView.setText(detection ? "ZAGROŻENIE" : "BEZPIECZNIE");

        if (detectionTextView.getText().equals("ZAGROŻENIE")) {
            detectionTextView.setTextColor(Color.RED);

        } else {
            detectionTextView.setTextColor(Color.GREEN);

        }


        return convertView;
    }
}

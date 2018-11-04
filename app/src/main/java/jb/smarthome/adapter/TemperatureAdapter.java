package jb.smarthome.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jb.smarthome.R;

import jb.smarthome.api.model.Temperature;

public class TemperatureAdapter extends ArrayAdapter<Temperature> {

    private Context mContext;
    int mResource;

    public TemperatureAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Temperature> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource =resource;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the temperature information
        String room = getItem(position).getRoom();
        String degrees = String.valueOf(getItem(position).getTemperature());
        String humidity = String.valueOf(getItem(position).getHumidity());


        //Create the object with the data
//        Temperature temperature = new Temperature(room,degrees,humidity);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView roomTextView = (TextView) convertView.findViewById(R.id.tempLayoutName);
        TextView degreesTextView = (TextView) convertView.findViewById(R.id.tempLayoutDegrees);
        TextView humidityTextView = (TextView) convertView.findViewById(R.id.tempLayoutHumidity);


        roomTextView.setText(room);
        degreesTextView.setText(degrees + mContext.getString(R.string.degreesC));
        humidityTextView.setText(humidity);

        return convertView;
    }
}

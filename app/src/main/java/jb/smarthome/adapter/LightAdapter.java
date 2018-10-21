package jb.smarthome.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import jb.smarthome.Light;
import jb.smarthome.R;


public class LightAdapter extends ArrayAdapter<Light> {

    private Context mContext;
    int mResource;

    public LightAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Light> objects) {
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
        Boolean isOn = getItem(position).getOn();



        //Create the object with the data
        Light light = new Light(room,isOn);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView lightRoom = (TextView) convertView.findViewById(R.id.lightRoom);
        Switch lightOnOff = (Switch) convertView.findViewById(R.id.lightOn);



        lightRoom.setText(room);
        lightOnOff.setChecked(isOn);


        return convertView;
    }
}

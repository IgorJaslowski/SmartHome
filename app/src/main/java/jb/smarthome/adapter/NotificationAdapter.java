package jb.smarthome.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jb.smarthome.api.model.Notification;
import jb.smarthome.R;

public class NotificationAdapter extends ArrayAdapter<Notification> {

    private Context mContext;
    int mResource;

    public NotificationAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Notification> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the temperature information
        String notifyDescription = getItem(position).getDescription().toString();
        String notifyDate = getItem(position).getDate();
        String notifyType = getItem(position).getType();


        //Create the object with the data
        Notification notification = new Notification(notifyDescription, notifyDate,notifyType);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView notifyDescriptionTextView = (TextView) convertView.findViewById(R.id.notifyDescription);
        TextView notifyDateTextView = (TextView) convertView.findViewById(R.id.notifyDate);
        View notifyTypeView = (View) convertView.findViewById(R.id.notifyType);
        ImageView icon = (ImageView) convertView.findViewById(R.id.notifyIcon);

   switch (notifyType){
            case "warning":
                notifyTypeView.setBackgroundColor(Color.RED);
                icon.setImageResource(R.drawable.ic_exclamation_circle_solid);
                break;
            case "good":
                notifyTypeView.setBackgroundColor(Color.GREEN);
                icon.setImageResource(R.drawable.ic_check_circle_solid);
                break;
            case "information":
                notifyTypeView.setBackgroundColor(Color.BLUE);
                icon.setImageResource(R.drawable.ic_check_circle_solid);
                break;
        }
        System.out.println(notification.toString());

        notifyDateTextView.setText(notifyDate.toString());
        notifyDescriptionTextView.setText(notifyDescription);

        return convertView;
    }
}

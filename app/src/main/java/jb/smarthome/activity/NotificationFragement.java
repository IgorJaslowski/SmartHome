package jb.smarthome.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import jb.smarthome.api.model.Notification;
import jb.smarthome.R;
import jb.smarthome.adapter.NotificationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragement extends Fragment {

    TextView desriptionTextView;
    TextView dateTextView;
    ListView listView;

    public NotificationFragement() {
        // Required empty public constructor
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        desriptionTextView = (TextView) getView().findViewById(R.id.notifyDescription);
        dateTextView = (TextView) getView().findViewById(R.id.notifyDate);
        listView = (ListView) getView().findViewById(R.id.notificationListView);

        ArrayList<Notification> notificationArrayList = new ArrayList<>();
        Notification notify1 = new Notification("Zapalono światło w Kuchnia",getDateTime());
        Notification notify2 = new Notification("Zapalono światło w Kuchnia",getDateTime());
        Notification notify3 = new Notification("Zapalono światło w Kuchnia",getDateTime());
        Notification notify4 = new Notification("Zapalono światło w Kuchnia",getDateTime());
        Notification notify5 = new Notification("Zapalono światło w Kuchnia",getDateTime());
        Notification notify6 = new Notification("Zapalono światło w Kuchnia",getDateTime());
        Notification notify7 = new Notification("Zapalono światło w Kuchnia",getDateTime());
        Notification notify8 = new Notification("Zapalono światło w Kuchnia",getDateTime());

        notificationArrayList.add(notify1);
        notificationArrayList.add(notify2);
        notificationArrayList.add(notify3);
        notificationArrayList.add(notify4);
        notificationArrayList.add(notify5);
        notificationArrayList.add(notify6);
        notificationArrayList.add(notify7);
        notificationArrayList.add(notify8);

        NotificationAdapter adapter = new NotificationAdapter(getContext(),R.layout.adapter_notification_view_layout,notificationArrayList);
        listView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        // Inflate the layout for this fragment
        LayoutInflater inflater1 = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View vi = inflater1.inflate(R.layout.activity_main2, null); //log.xml is your file.
        TextView tv = (TextView)vi.findViewById(R.id.fragmentHeader); //get a reference to the textview on the log.xml file.
        tv.setText("Powiadomienia");
        return view;
    }

}

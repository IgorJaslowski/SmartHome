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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(user.getUid()).child("Powiadomienia");

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

        /*Loading notifications from REALTIME DATABASE and added to notification fragment */
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Notification> notificationArrayList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    notificationArrayList.add(new Notification(postSnapshot.getValue().toString(), postSnapshot.getKey()));
                }
                NotificationAdapter adapter = new NotificationAdapter(getContext(), R.layout.adapter_notification_view_layout, notificationArrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        return view;
    }

}

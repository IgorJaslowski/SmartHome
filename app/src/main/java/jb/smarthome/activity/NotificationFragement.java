package jb.smarthome.activity;


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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

import jb.smarthome.R;
import jb.smarthome.adapter.NotificationAdapter;
import jb.smarthome.api.model.Notification;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragement extends Fragment {

    TextView desriptionTextView;
    TextView dateTextView;
    ListView listView;
    private ArrayList<Notification> data;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    ArrayList<Notification> notificationArrayList = new ArrayList<>();


    public NotificationFragement() {
        // Required empty public constructor
    }

    public static NotificationFragement newInstance(ArrayList<Notification> data) {
        NotificationFragement fragement = new NotificationFragement();
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        fragement.setArguments(args);

        return fragement;
    }

    @Override
    public void onResume() {
        super.onResume();


       /* myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Notification> notificationArrayList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    notificationArrayList.add(new Notification(
                            postSnapshot.getValue().toString().split(",")[0].substring(1),
                            postSnapshot.getKey(),
                            postSnapshot.getValue().toString().split(",")[1].
                                    substring(1, postSnapshot.getValue().toString().split(",")[1].length()-1)));

                }
                Collections.reverse(notificationArrayList);
                NotificationAdapter adapter = new NotificationAdapter(getActivity(), R.layout.adapter_notification_view_layout, notificationArrayList);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                System.out.println("@@@@@@@@@@@@@@@@" + adapter.getItem(0));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        desriptionTextView = (TextView) view.findViewById(R.id.notifyDescription);
        dateTextView = (TextView) view.findViewById(R.id.notifyDate);
        listView = (ListView) view.findViewById(R.id.notificationListView);


       // Collections.reverse(notificationArrayList);

        NotificationAdapter adapter = new NotificationAdapter(getActivity(), R.layout.adapter_notification_view_layout, notificationArrayList);
        listView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference(user.getUid()).child("Powiadomienia").orderByKey();
        Query myRef = database.getReference("Powiadomienia");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                System.out.println(dataSnapshot.getValue());
                notificationArrayList.add(
                        //getValue().toString().split(",")[1].length()-1))
                        new Notification(dataSnapshot.getValue().toString().split(",")[0].substring(1),
                                dataSnapshot.getKey(),
                                dataSnapshot.getValue().toString().split(",")[1].
                                        substring(1, dataSnapshot.getValue().toString().split(",")[1].length()-1)));
                adapter.notifyDataSetChanged();
                Collections.reverse(notificationArrayList);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}

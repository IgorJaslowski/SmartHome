package jb.smarthome.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jb.smarthome.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragement extends Fragment {


    public NotificationFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_fragement, container, false);
    }

}

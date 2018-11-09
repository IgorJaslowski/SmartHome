package jb.smarthome.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jb.smarthome.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        LayoutInflater inflater1 = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View vi = inflater1.inflate(R.layout.activity_main2, null); //log.xml is your file.
        TextView tv = (TextView)vi.findViewById(R.id.fragmentHeader); //get a reference to the textview on the log.xml file.
        tv.setText("Home");
        return view;
    }

}

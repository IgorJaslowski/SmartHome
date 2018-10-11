package jb.smarthome.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jb.smarthome.LogItem;
import jb.smarthome.R;
import jb.smarthome.adapter.ImageAdapter;
import jb.smarthome.adapter.LogAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements AdapterView.OnItemClickListener {

    String[] logs;
    List<LogItem> logItems;
    ListView logListView;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        GridView grid = (GridView) view.findViewById(R.id.gridview);

        grid.setAdapter(new ImageAdapter(view.getContext()));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), LightActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), TemperatureActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), LightActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), LightActivity.class));
                        break;
                }

            }
        });

       /* logItems = new ArrayList<LogItem>();
        logs = getResources().getStringArray(R.array.Logs);

        for (int i = logs.length - 1; i >= 0; i--) {
            LogItem logItem = new LogItem(i, logs[i]);
            logItems.add(logItem);
        }

        logListView = (ListView) view.findViewById(R.id.log_list_view);
        LogAdapter adapter = new LogAdapter(view.getContext(), logItems);
        logListView.setAdapter(adapter);*/

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

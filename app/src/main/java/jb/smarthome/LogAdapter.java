package jb.smarthome;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LogAdapter extends BaseAdapter {
    private Context context;
    private List<LogItem> logItems;

    public LogAdapter(Context context, List<LogItem> logItems) {
        this.context = context;
        this.logItems = logItems;
    }

    @Override
    public int getCount() {
        return logItems.size();
    }

    @Override
    public Object getItem(int position) {
        return logItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return logItems.indexOf(getItem(position));
    }
    private class ViewHolder{
        TextView textLog;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.log_list_item,null);
            holder = new ViewHolder();

            holder.textLog = (TextView) convertView.findViewById(R.id.textLog);

            LogItem log_pos = logItems.get(position);

            holder.textLog.setText(log_pos.getText());
        }
        return convertView;
    }




}

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
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.activity.LightActivity;
import jb.smarthome.api.model.Light;
import jb.smarthome.R;
import jb.smarthome.api.service.LightService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LightAdapter extends ArrayAdapter<Light> {
    Switch lightOnOff;
    private Context mContext;
    int mResource;

    public LightAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Light> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the temperature information
        final ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }


        final Light light = getItem(position);

        TextView lightRoom = (TextView) convertView.findViewById(R.id.lightRoom);

        lightRoom.setText(light.getRoom());
       // System.out.println(holder.lightSwitch.isChecked());
        holder.lightSwitch.setChecked(light.getIsOn());
        System.out.println(holder.lightSwitch.isChecked());


        holder.lightSwitch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(holder.lightSwitch.isChecked()){
                    //holder.lightSwitch.setChecked(false);
                    turnOn(light,holder);

                }else {
                    turnOff(light,holder);
                    //holder.lightSwitch.setChecked(true);

                }
            }
        });
        return convertView;
    }

   private void turnOn(Light light, final ViewHolder holder){
       final LightService service = RetrofitClientInstance.getRetrofitInstance().create(LightService.class);
       Call<Void> call = service.turnOnLed(light.getPin());
       call.enqueue(new Callback<Void>() {
           @Override
           public void onResponse(Call<Void> call, Response<Void> response) {
           }

           @Override
           public void onFailure(Call<Void> call, Throwable t) {
           }
       });
   }
    private void turnOff(Light light, final ViewHolder holder){
        final LightService service = RetrofitClientInstance.getRetrofitInstance().create(LightService.class);
        Call<Void> call = service.turnOffLed(light.getPin());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    static class ViewHolder {
        @BindView(R.id.lightSwitch)
        Switch lightSwitch;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


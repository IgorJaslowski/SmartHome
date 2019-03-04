package jb.smarthome.api.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlarmService {
    @GET("/alarmStatus")
    Call<Boolean> alarmStatus();
    @GET("/turnOnAlarm")
    Call<Void> alarmOn();
    @GET("/turnOffAlarm")
    Call<Void> alarmOff();
    @GET("/alarm")
    Call<Void> alarm();
}

package jb.smarthome.api.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SensorService {
    @GET("/gasSensor")
    Call<String> gasSensor();
    @GET("/fireSensor")
    Call<String> fireSensor();
    @GET("/motionSensor")
    Call<Boolean> motionSensor();
}

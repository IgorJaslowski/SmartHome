package jb.smarthome.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TemperatureService {
    @GET("/getTemp")
    Call<Float> getTemp();
}

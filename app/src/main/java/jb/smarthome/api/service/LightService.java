package jb.smarthome.api.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LightService {
    @GET("/ledState")
    Call<Boolean> getState();
    @GET("/turnOnLed")
    Call<Void> turnOnLed();
    @GET("/turnOffLed")
    Call<Void> turnOffLed();
}

package jb.smarthome.api.service;

import jb.smarthome.api.model.LightResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LightService {
    @GET("/ledState")
    Call<LightResponse> getState();
    @GET("/turnOn")
    Call<Void> turnOnLed(@Query("pin")int pin);
    @GET("/turnOff")
    Call<Void> turnOffLed(@Query("pin")int pin);
    @GET("/turnAllOn")
    Call<Void> turnAllOn();
    @GET("/turnAllOn")
    Call<Void> turnAllOff();
}

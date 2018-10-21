package jb.smarthome.api.service;

import jb.smarthome.api.model.TemperatureResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TemperatureService {
    @GET("/temperatureAndHumidity")
    Call<TemperatureResponse> getTemperatureAndHumidity();
}

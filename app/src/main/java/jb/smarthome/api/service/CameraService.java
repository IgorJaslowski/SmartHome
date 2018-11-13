package jb.smarthome.api.service;

import jb.smarthome.api.model.TemperatureResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CameraService {
    @GET("/turnLeft")
    Call<Integer> turnLeft();
    @GET("/centerServo")
    Call<Void> centerServo();
    @GET("/turnRight")
    Call<Integer> turnRight();
}

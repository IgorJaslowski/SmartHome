package jb.smarthome.api.model;

import java.util.ArrayList;

public class LightResponse {
    private ArrayList<Light> lightList;

    @Override
    public String toString() {
        return "LightResponse{" +
                "lightList=" + lightList +
                '}';
    }

    public ArrayList<Light> getLightList() {
        return lightList;
    }

    public void setLightList(ArrayList<Light> lightList) {
        this.lightList = lightList;
    }

    public LightResponse(ArrayList<Light> lightList) {

        this.lightList = lightList;
    }
}

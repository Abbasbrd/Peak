package com.anar.PEAK;

import java.io.Serializable;

public class LocationPoint implements Serializable {

    //fields

    private Double postLongtitude;
    private Double postLattitude;

    //costructors
    public LocationPoint(){}

    public LocationPoint( Double longtitude, Double lattitude){
        this.postLattitude =lattitude;
        this.postLongtitude =longtitude;

    }


    //properties
    public Double getPostLongtitude() {
        return postLongtitude;
    }

    public void setPostLongtitude(Double postLongtitude) {
        this.postLongtitude = postLongtitude;
    }

    public Double getPostLattitude() {
        return postLattitude;
    }

    public void setPostLattitude(Double postLattitude) {
        this.postLattitude = postLattitude;
    }



}

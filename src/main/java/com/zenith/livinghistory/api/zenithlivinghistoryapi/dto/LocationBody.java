package com.zenith.livinghistory.api.zenithlivinghistoryapi.dto;

import org.joda.time.DateTime;

import java.io.Serializable;

public class LocationBody implements Serializable {

    /*
    * Example:
    *
    * "location": {
    * "longitude": -83.6945691,
    * "latitude": 42.25475478
  	* },
    *
    * */

    public LocationBody() {
    }

    public LocationBody(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private double longitude;

    private double latitude;


    public double getLongitude() { return longitude; }

	public void setLongitude (double longitude) { this.longitude = longitude; }


	public double getLatitude () { return latitude; }

	public void setLatitude (double latitude) { this.latitude = latitude; }



}


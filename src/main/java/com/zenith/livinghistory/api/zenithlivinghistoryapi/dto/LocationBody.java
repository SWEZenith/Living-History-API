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
    * "name": Bebek,
    * "tags": ["Bebek", "Istanbul", "Turkey"]
  	* },
    *
    * */

    //region Private Members

    private String name ;

    private double longitude;

    private double latitude;

    private String[] tags;

    //endregion

    //region Constructor

    public LocationBody() {
    }

    public LocationBody(double longitude, double latitude, String name) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }
    //endregion

    //region Getter and Setters

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public double getLongitude() { return longitude; }

	public void setLongitude (double longitude) { this.longitude = longitude; }

	public double getLatitude () { return latitude; }

	public void setLatitude (double latitude) { this.latitude = latitude; }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
    //endregion

}


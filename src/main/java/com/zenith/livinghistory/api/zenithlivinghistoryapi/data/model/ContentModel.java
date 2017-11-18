package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.utils.CascadeSave;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "Contents")
public class ContentModel implements Serializable {
    /*
    * Example:
    *
  	* {
    * "contentType": "Image",
    * "title": "Bebek was not so crowded",
    * "description": "https://goo.gl/XQxhTt",
    * "tags": ["Bebek", "2015", "Seaside"],
    * "date": "2017-10-31",
    * "location": {
    * "longitude": -83.6945691,
    * "latitude": 42.25475478
    * },
    * "creator": "http://example.net/api/v1/users/pinar"
	* }
    *
    * */

    @JsonProperty("@contentType")
    private String contentType;

    @Id
    private String id;

    private String title;

    @Indexed
    private String description;

	private String[] tags;

    private DateTime date;

    private LocationBody location;

    private String creator;

    @DBRef
    @CascadeSave
    @Field("annotations")
    private List<AnnotationModel> annotations = new ArrayList<>();

    public List<AnnotationModel> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationModel> annotations) {
        this.annotations = annotations;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

	public String[] getTags() { return tags; }

	public void setTags(String[] tags) { this.tags = tags; }

	public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public LocationBody getLocation() {
        return location;
    }

    public void setLocation(LocationBody location) {
        this.location = location;
    }

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

        private double longitude;

        private double latitude;


        public double getLongitude() { return longitude; }

        public void setLongitude (double longitude) { this.longitude = longitude; }


        public double getLatitude () { return latitude; }

        public void setLatitude (double latitude) { this.latitude = latitude; }
    }
}

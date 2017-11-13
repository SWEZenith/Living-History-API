package com.zenith.livinghistory.api.zenithlivinghistoryapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "Contents")
public class Content implements Serializable {

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

    public Content() {
    }

    public Content(String contentType, String title, String description, String[] tags, DateTime date, LocationBody location, String creator) {

		this.contentType = contentType;
		this.title = title;
		this.description = description;
		this.tags = tags;
		this.date = date;
		this.location = location;
		this.creator = creator;
	}

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

//@onetomanyrelationship
	//private List<Annotation> annotations;

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

}

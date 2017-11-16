package com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.ContentItemModel;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.LocationBody;
import org.joda.time.DateTime;

import java.util.List;

public class HistoryRequest {
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

    public HistoryRequest() {
    }

    public HistoryRequest(String contentType, String title, String description, String[] tags, DateTime date, LocationBody location, String creator) {
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

    private String id;

    private String title;

    private String description;

	private String[] tags;

    private DateTime date;

    private LocationBody location;

    // jsonld creator id
    private String creator;

    private List<AnnotationRequest> annotations;

    @JsonProperty("history_items")
    private List<ContentItemModel> historyItems;


    public List<ContentItemModel> getHistoryItems() {
        return historyItems;
    }

    public void setHistoryItems(List<ContentItemModel> historyItems) {
        this.historyItems = historyItems;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public LocationBody getLocation() {
        return location;
    }

    public void setLocation(LocationBody location) {
        this.location = location;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<AnnotationRequest> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationRequest> annotations) {
        this.annotations = annotations;
    }
}

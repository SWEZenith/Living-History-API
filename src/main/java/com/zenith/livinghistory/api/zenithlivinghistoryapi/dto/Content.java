package com.zenith.livinghistory.api.zenithlivinghistoryapi.dto;

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
public class Content implements Serializable {

    /*
    * Example:
    *
  	* {
    * "id": <id>
    * "url": http://zenith.com/contents/[id]
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


	private String url;

    @Id
    private String id;

    private String title;

	private String[] tags;

	@JsonProperty ("story_items")
	@Field ("story_items")
	private List<StoryItem> storyItems = new ArrayList<>();

	private int day;

	private int month;

	private int year;

    private LocationBody location;

    private String creator;

    @DBRef
    @CascadeSave
    @Field("annotations")
    private List<Annotation> annotations = new ArrayList<>();

    public String getCoverImage() {
        return this.storyItems
                .stream()
                .filter(item -> item.getType().equals("image"))
                .findFirst()
                .map(StoryItem::getContent)
                .orElse("");
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @JsonProperty("cover_image")
    private String coverImage;

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

	public String getUrl() {
		return "http://living-history.gkc.host/contents/" + this.id;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

	public List<StoryItem> getStoryItems() { return storyItems; }

	public void setStoryItems(List<StoryItem> storyItems) { this.storyItems = storyItems; }
	public String[] getTags() { return tags; }

	public void setTags(String[] tags) { this.tags = tags; }

	public int getDay() { return day; }

	public void setDay(int day) { this.day = day; }

	public int getMonth() { return month; }

	public void setMonth(int month) { this.month = month; }

	public int getYear() { return year; }

	public void setYear(int year) { this.year = year; }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public LocationBody getLocation() {
        return location;
    }

    public void setLocation(LocationBody location) {
        this.location = location;
    }

}

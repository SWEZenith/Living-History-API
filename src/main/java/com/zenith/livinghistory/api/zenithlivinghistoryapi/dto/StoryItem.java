package com.zenith.livinghistory.api.zenithlivinghistoryapi.dto;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.print.DocFlavor;
import java.io.Serializable;

@Document (collection = "StoryItems")
public class StoryItem implements Serializable {

	/*
    * Example:
    * {
    * "id": "http://"
    * "type": "text",
    * "content": "Bebek was a popular residential district under Ottoman rule, and continues to be so today. Its population reflected the diverse society of the time, which is still visible in Bebek's historic architecture and contemporary constituencies.",
    * "id": null
    * },
    *
    * */

	private String _id = new ObjectId().toString();

	private String type;

	private String content;


	public String getId() {
		return _id;
	}

	public void setId(String _id) {
		this._id = _id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

package com.zenith.livinghistory.api.zenithlivinghistoryapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;

import java.io.Serializable;

public class AnnotationBody implements Serializable {

    /*
    * Example:
    *
    * "body": {
    *   "type" : "TextualBody",
    *   "value" : "<p>Paragraf!</p>",
    *   "format" : "text/html",
    *   "language" : "tr",
    *   "creator": "http://example.net/user2",
    *   "created": "2014-06-02T17:00:00Z"
    * }
    *
    * */

    public AnnotationBody() {
    }

    public AnnotationBody(String type, String value, String format, String language, String creator, DateTime created) {
        this.type = type;
        this.value = value;
        this.format = format;
        this.language = language;
        this.creator = creator;
        this.created = created;
    }

    private String type;

    private String value;

    private String format;

    private String language;

    private String creator;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private DateTime created;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}


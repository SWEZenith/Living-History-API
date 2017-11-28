package com.zenith.livinghistory.api.zenithlivinghistoryapi;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.enums.UserStatus;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.enums.UserType;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.*;
import org.joda.time.DateTime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Example {
    public static Annotation GetAnnotationInstance() {
        final String CONTEXT = "http://www.w3.org/ns/anno.jsonld";
        final String TYPE = "Annotation";
        final String CREATOR = "http://living-history.gkc.host/api/v1/users/gokce";
        final String BODY_TYPE = AnnotationType.TEXT;
        final String BODY_VALUE = "<p>Bir paragraf!</p>";
        final String BODY_FORMAT = "text/html";
        final String BODY_LANGUAGE = "tr";
        final String TARGET_ID = "http://example.com/image2.jpg#xywh=200,200,400,500";
        final String TARGET_TYPE = "Image";
        final String TARGET_FORMAT = "image/jpeg";

        AnnotationBody annotationBody = new AnnotationBody();
        annotationBody.setType(BODY_TYPE);
        annotationBody.setValue(BODY_VALUE);
        annotationBody.setFormat(BODY_FORMAT);
        annotationBody.setLanguage(BODY_LANGUAGE);
        annotationBody.setCreator(CREATOR);
        annotationBody.setCreated(DateTime.parse("2014-06-02T17:00:00Z"));

        AnnotationTarget annotationTarget = new AnnotationTarget();
        annotationTarget.setId(TARGET_ID);
        annotationTarget.setType(TARGET_TYPE);
        annotationTarget.setFormat(TARGET_FORMAT);

        Annotation annotation = new Annotation();
        annotation.setContext(CONTEXT);
        annotation.setType(TYPE);
        annotation.setCreator(CREATOR);
        annotation.setCreated(DateTime.parse("2015-01-28T12:00:00Z"));
        annotation.setModified(DateTime.parse("2015-01-29T09:00:00Z"));
        annotation.setBody(annotationBody);
        annotation.setTarget(annotationTarget);

        return annotation;
    }



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
    public static Content GetContentInstance(){
		final String URL = "Annotation";
		final String TITLE = "Annotation";
		final String DESCRIPTION = "Annotation";
		final String TAGS = "Annotation";
		final String DATE = "Annotation";
		final String LOCATION = "Annotation";
		final String LONGITUDE = "Annotation";
		final String LATITUDE = "Annotation";
		final String CREATOR = "Annotation";


		Content content = new Content();
		content.setUrl(URL);
		content.setTitle(TITLE);

		StoryItem si = new StoryItem();
		si.setContent("foo");


		List<StoryItem> list = new ArrayList<StoryItem>();

		list.add(si);

		content.setStoryItems(list);


		ArrayList<String> stringList = new ArrayList<String>();

		stringList.add("foo");

		content.setTags(null);

		content.setDay(5);

		content.setMonth(5);

		content.setLocation(new LocationBody());

		content.setCreator("foo");

		return content;
	}


	public static User GetUserInstance(){
		final String  username = "foo";
		final String  password = "foo";
		final String  email = "foo";
		final UserStatus status =  UserStatus.ACTIVE;
		final UserType type =  UserType.STANDARD;



		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setStatus(status);
		user.setType(type);



		return user;
	}
}

package com.zenith.livinghistory.api.zenithlivinghistoryapi;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request.AnnotationRequest;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request.AnnotationBody;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.AnnotationTarget;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.AnnotationType;
import org.joda.time.DateTime;

public class Example {
    public static AnnotationRequest GetAnnotationInstance() {
        final String CONTEXT = "http://www.w3.org/ns/anno.jsonld";
        final String TYPE = "AnnotationRequest";
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

        AnnotationRequest annotation = new AnnotationRequest();
        annotation.setContext(CONTEXT);
        annotation.setType(TYPE);
        annotation.setCreator(CREATOR);
        annotation.setCreated(DateTime.parse("2015-01-28T12:00:00Z"));
        annotation.setModified(DateTime.parse("2015-01-29T09:00:00Z"));
        annotation.setBody(annotationBody);
        annotation.setTarget(annotationTarget);

        return annotation;
    }
}

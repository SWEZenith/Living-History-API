package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.*;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AnnotationRepositoryTest {
    @Autowired
    private AnnotationRepository annotationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void whenFindByCreator_thenReturnAnnotation() {
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

        DateTime now = DateTime.now();

        AnnotationBody annotationBody = new AnnotationBody();
        annotationBody.setType(BODY_TYPE);
        annotationBody.setValue(BODY_VALUE);
        annotationBody.setFormat(BODY_FORMAT);
        annotationBody.setLanguage(BODY_LANGUAGE);
        annotationBody.setCreator(CREATOR);
        annotationBody.setCreated(now);

        AnnotationTarget annotationTarget = new AnnotationTarget();
        annotationTarget.setId(TARGET_ID);
        annotationTarget.setType(TARGET_TYPE);
        annotationTarget.setFormat(TARGET_FORMAT);

        Annotation annotation = new Annotation();
        annotation.setContext(CONTEXT);
        annotation.setType(TYPE);
        annotation.setCreator(CREATOR);
        annotation.setCreated(now);
        annotation.setBody(annotationBody);
        annotation.setTarget(annotationTarget);

        mongoTemplate.save(annotation);

        Annotation foundAnnotation = annotationRepository.findFirstByCreator(CREATOR);

        assertThat(foundAnnotation.getCreator(), is(equalTo(annotation.getCreator())));
    }
}

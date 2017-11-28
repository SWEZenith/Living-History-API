package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.Example;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.AnnotationBody;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest()
public class AnnotationBodyTest {
    @Autowired
    private AnnotationRepository annotationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void whenFindByCreator_thenReturnAnnotation() {
        AnnotationBody annotationBody = Example.GetAnnotationInstance().getBody();

        mongoTemplate.save(annotationBody);

        AnnotationBody foundAnnotationBody = annotationRepository.findFirstByCreator(annotationBody.getCreator()).getBody();

        // Exclude date fields to prevent false positive assertion errors caused by timezone.
        Assert.assertThat(annotationBody, new ReflectionEquals(foundAnnotationBody, "target", "body", "created", "modified"));
        Assert.assertThat(annotationBody.getCreator(), new ReflectionEquals(foundAnnotationBody.getCreator(), "created"));
		Assert.assertThat(annotationBody.getLanguage(), new ReflectionEquals(foundAnnotationBody.getLanguage(), "created"));
		Assert.assertThat(annotationBody.getFormat(), new ReflectionEquals(foundAnnotationBody.getFormat(), "created"));
	}
}

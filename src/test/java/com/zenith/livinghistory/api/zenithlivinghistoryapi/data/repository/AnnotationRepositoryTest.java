package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.Example;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request.AnnotationRequest;
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
public class AnnotationRepositoryTest {
    @Autowired
    private AnnotationRepository annotationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void whenFindByCreator_thenReturnAnnotation() {
        AnnotationRequest annotation = Example.GetAnnotationInstance();

        mongoTemplate.save(annotation);

        AnnotationRequest foundAnnotation = annotationRepository.findFirstByCreator(annotation.getCreator());

        // Exclude date fields to prevent false positive assertion errors caused by timezone.
        Assert.assertThat(annotation, new ReflectionEquals(foundAnnotation, "target", "body", "created", "modified"));
        Assert.assertThat(annotation.getBody(), new ReflectionEquals(foundAnnotation.getBody(), "created"));
        Assert.assertThat(annotation.getTarget(), new ReflectionEquals(foundAnnotation.getTarget(), "created"));
    }
}

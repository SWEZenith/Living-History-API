package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.Example;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.AnnotationBody;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
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
public class ContentTest {
    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void whenFindByCreator_thenReturnAnnotation() {
        Content content = Example.GetContentInstance();

        mongoTemplate.save(content);

		Content foundContent = contentRepository.findOne(content.getId());

        // Exclude date fields to prevent false positive assertion errors caused by timezone.
        Assert.assertThat(content, new ReflectionEquals(foundContent, "target", "body", "createdAt", "modified"));

        Assert.assertThat(content.getCreator(), new ReflectionEquals(foundContent.getCreator(), "createdAt"));

        Assert.assertThat(content.getAnnotations(), new ReflectionEquals(foundContent.getAnnotations(), "createdAt"));

        Assert.assertThat(content.getTitle(), new ReflectionEquals(foundContent.getTitle(), "createdAt"));

		Assert.assertThat(content.getYear(), new ReflectionEquals(foundContent.getYear(), "createdAt"));

		Assert.assertThat(content.getDay(), new ReflectionEquals(foundContent.getDay(), "createdAt"));

		Assert.assertThat(content.getLocation(), new ReflectionEquals(foundContent.getLocation(), "createdAt"));

		Assert.assertThat(content.getMonth(), new ReflectionEquals(foundContent.getMonth(), "createdAt"));

		Assert.assertThat(content.getStoryItems(), new ReflectionEquals(foundContent.getStoryItems(), "createdAt"));

		Assert.assertThat(content.getUrl(), new ReflectionEquals(foundContent.getUrl(), "createdAt"));


	}
}

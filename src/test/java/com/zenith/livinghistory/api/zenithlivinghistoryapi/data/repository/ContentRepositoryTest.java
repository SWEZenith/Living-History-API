package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ContentRepositoryTest {
    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void whenFindByCreator_thenReturnContent() throws IOException {
        File jsonFile = new ClassPathResource("content.json").getFile();
        BufferedReader reader = Files.newBufferedReader(jsonFile.toPath());
        String jsonString = reader.lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        Content content = mapper.readValue(jsonString, Content.class);

        mongoTemplate.save(content);

        Content foundContent = contentRepository.findContentsByUsername("pinar").get(0);

        Assert.assertThat(content.getCreator(), new ReflectionEquals(foundContent.getCreator()));
    }
}

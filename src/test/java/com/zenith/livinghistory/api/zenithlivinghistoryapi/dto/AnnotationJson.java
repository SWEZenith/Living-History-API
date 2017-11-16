package com.zenith.livinghistory.api.zenithlivinghistoryapi.dto;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.Example;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request.AnnotationRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@JsonTest
public class AnnotationJson {

    @Autowired
    private JacksonTester<AnnotationRequest> json;

    @Test
    public void testSerialize() throws Exception {
        AnnotationRequest annotation = Example.GetAnnotationInstance();
        File jsonFile = new ClassPathResource("annotation.json").getFile();
        assertThat(this.json.write(annotation)).isEqualToJson(jsonFile);
    }

    @Test
    public void testDeserialize() throws Exception {
        File jsonFile = new ClassPathResource("annotation.json").getFile();
        BufferedReader reader = Files.newBufferedReader(jsonFile.toPath());
        String jsonString = reader.lines().collect(Collectors.joining());
        AnnotationRequest annotationExpected = Example.GetAnnotationInstance();
        AnnotationRequest annotationActual = this.json.parseObject(jsonString);

        Assert.assertThat(annotationExpected, new ReflectionEquals(annotationActual, "target", "body"));
        Assert.assertThat(annotationExpected.getBody(), new ReflectionEquals(annotationActual.getBody()));
        Assert.assertThat(annotationExpected.getTarget(), new ReflectionEquals(annotationActual.getTarget()));
    }
}

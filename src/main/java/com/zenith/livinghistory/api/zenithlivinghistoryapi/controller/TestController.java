package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.core.JsonLdError;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.AnnotationValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/v1/test")
public class TestController {
    private ObjectMapper mapper;

    public TestController() {
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(method = RequestMethod.GET)
    @SuppressWarnings("unchecked")
    public ResponseEntity<Map> test() throws IOException {
        String jsonString = "{\n" +
                "   \"@context\": \"http://www.w3.org/ns/anno.jsonld\",\n" +
                "   \"id\": \"http://example.org/anno3\",\n" +
                "   \"type\": \"Annotation\",\n" +
                "   \"creator\": \"http://example.org/user1\",\n" +
                "   \"created\": \"2015-01-28T12:00:00Z\",\n" +
                "   \"modified\": \"2015-01-29T09:00:00Z\",\n" +
                "   \"body\": {\n" +
                "       \"type\" : \"TextualBody\",\n" +
                "       \"value\" : \"<p>Paragraf!</p>\",\n" +
                "       \"format\" : \"text/html\",\n" +
                "       \"language\" : \"tr\",\n" +
                "       \"creator\": \"http://example.net/user2\",\n" +
                "       \"created\": \"2014-06-02T17:00:00Z\"\n" +
                "   },\n" +
                "   \"target\": {\n" +
                "       \"id\": \"http://example.com/image1.jpg#xywh=100,100,300,300\",\n" +
                "       \"type\": \"Image\",\n" +
                "       \"format\": \"image/jpeg\"\n" +
                "   }\n" +
                "}";

        Map<String, Object> annotationExample = this.mapper.readValue(jsonString, Map.class);

        return new ResponseEntity<>(annotationExample, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Annotation> test(@RequestBody Annotation annotation) throws IOException, JsonLdError {
        String jsonString = AnnotationValidator.validate(annotation);
        Annotation response = this.mapper.readValue(jsonString, Annotation.class);
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }
}

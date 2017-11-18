package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.github.jsonldjava.core.JsonLdError;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.AnnotationValidator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String test() {
        return "{\n" +
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
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public String test(@RequestBody Annotation annotation) throws IOException, JsonLdError {
        return AnnotationValidator.validate(annotation);
    }
}

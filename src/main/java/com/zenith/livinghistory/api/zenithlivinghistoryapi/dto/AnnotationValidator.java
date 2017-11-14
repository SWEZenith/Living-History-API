package com.zenith.livinghistory.api.zenithlivinghistoryapi.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnnotationValidator implements ConstraintValidator<AnnotationValid, Annotation> {

    @Override
    public void initialize(AnnotationValid annotationValid) {

    }

    @Override
    public boolean isValid(Annotation annotationValue, ConstraintValidatorContext context) {
        try {
            validate(annotationValue);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String validate(Annotation annotation) throws IOException, JsonLdError {

        ObjectMapper mp = new ObjectMapper();
        Object jsonObject = mp.convertValue(annotation, LinkedHashMap.class);

        Map context = new HashMap();
        context.put("@context", "http://www.w3.org/ns/anno.jsonld");

        JsonLdOptions options = new JsonLdOptions();

        Object compact = JsonLdProcessor.compact(jsonObject, context, options);

        return JsonUtils.toPrettyString(compact);
    }
}

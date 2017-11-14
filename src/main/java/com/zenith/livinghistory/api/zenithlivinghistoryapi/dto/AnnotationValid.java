package com.zenith.livinghistory.api.zenithlivinghistoryapi.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = {AnnotationValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target( {TYPE} )
public @interface AnnotationValid {

    String message() default "Invalid annotation: Cannot validate JSON-LD";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package com.krisjanis.balodis.model.validators;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface UniqueField {
	String message() default "Duplicate record!";

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

	/**
	* The mapped hibernate/jpa entity class
	*/
	Class<?> entity();

	/**
	* The property of the entity we want to validate for uniqueness.
	*/
	String property() default "";   
}


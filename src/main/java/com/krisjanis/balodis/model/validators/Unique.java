package com.krisjanis.balodis.model.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
 
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.krisjanis.balodis.model.validators.UniqueValidator;
 
@Documented
@Constraint(validatedBy = { UniqueValidator.class })
@Target({ TYPE, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface Unique {
    String message() default "Duplicate entry error!";
 
    String[] properties();
 
    Class<?>[] groups() default { };
 
    Class<? extends Payload>[] payload() default { };
}

package demo.spring.angular.auth.web.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import demo.spring.angular.auth.web.validator.TokenValidator;

@Target({ TYPE, FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = TokenValidator.class)
@Documented
public @interface TokenValid {

    String message() default "Invalid Token";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

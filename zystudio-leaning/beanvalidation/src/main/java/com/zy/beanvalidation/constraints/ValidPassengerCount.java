package com.zy.beanvalidation.constraints;

import com.zy.beanvalidation.chapter01.Car;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { ValidPassengerCount.ValidPartValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface ValidPassengerCount {
    String message() default "{com.zy.beanvalidation.constraints.ValidPassengerCount.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    class ValidPartValidator
            implements ConstraintValidator<ValidPassengerCount, Car> {

        @Override
        public void initialize(ValidPassengerCount annotation) {
        }

        @Override
        public boolean isValid(Car car, ConstraintValidatorContext context) {
            return false;//car.getSeatCount() > car.getParts().size();
        }
    }
}
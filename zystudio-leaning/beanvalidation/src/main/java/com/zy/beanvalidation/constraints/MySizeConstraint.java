package com.zy.beanvalidation.constraints;

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
@Constraint(validatedBy = { MySizeConstraint.MaxAllowedFuelConsumptionValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface MySizeConstraint {

    String message() default "{com.zy.beanvalidation.constraints.MySizeConstraint.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    class MaxAllowedFuelConsumptionValidator implements ConstraintValidator<MySizeConstraint, String> {
        @Override
        public void initialize(MySizeConstraint annotation) {
        }
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            System.out.println("the MySizeConstraint isValid executed.");
            if ( value == null ) {
                return false;
            }
            return false;
        }
    }

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        MySizeConstraint[] value();
    }
}
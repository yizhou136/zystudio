package com.zy.ddd.support.spring.annotation;

import java.lang.annotation.*;

/**
 * @author by zy.
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DomainMethodEventListener {

    Class<?>[] classes() default {};
}
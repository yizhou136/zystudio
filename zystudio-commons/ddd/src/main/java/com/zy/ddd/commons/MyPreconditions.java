package com.zy.ddd.commons;

/**
 * @author by zy.
 */
public class MyPreconditions {

    public static <T> T requireNonNull(T obj, RuntimeException exception) {
        if (obj == null) {
            throw exception;
        }
        return obj;
    }
}

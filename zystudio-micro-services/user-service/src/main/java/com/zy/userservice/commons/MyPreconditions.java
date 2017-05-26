package com.zy.userservice.commons;

import com.zy.userservice.application.exception.AppServiceException;

import java.util.function.Supplier;

/**
 * @author by zy.
 */
public class MyPreconditions {

    public static <T, E extends AppServiceException> T
                    requireNonNull(T obj,
                                Supplier<String> supplier, Class<E> eClass) {
        if (obj == null) {
            throw AppServiceException.of(supplier, eClass);
        }

        return obj;
    }

    public static <E extends AppServiceException> void
                checkArgument(boolean express,
                   Supplier<String> supplier, Class<E> eClass) {
        if (!express) {
            throw AppServiceException.of(supplier, eClass);
        }
    }
}
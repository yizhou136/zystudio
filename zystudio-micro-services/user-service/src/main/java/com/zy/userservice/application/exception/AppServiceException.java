package com.zy.userservice.application.exception;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

/**
 * @author by zy.
 */
public class AppServiceException extends RuntimeException{

    public AppServiceException() {
        super();
    }

    public AppServiceException(String message) {
        super(message);
    }

    public AppServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppServiceException(Throwable cause) {
        super(cause);
    }

    public static <E extends AppServiceException> E of(Supplier<String> supplier,
                                                       Class<E> eClass){
        try {
            Constructor<E> constructor = eClass.getConstructor(String.class);
            E e = constructor.newInstance(supplier.get());
            return e;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}

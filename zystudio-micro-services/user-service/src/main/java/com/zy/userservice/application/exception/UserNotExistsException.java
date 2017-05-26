package com.zy.userservice.application.exception;

/**
 * @author by zy.
 */
public class UserNotExistsException extends AppServiceException{

    public UserNotExistsException(String message) {
        super(message);
    }
}

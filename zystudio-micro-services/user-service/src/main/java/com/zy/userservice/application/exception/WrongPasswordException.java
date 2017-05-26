package com.zy.userservice.application.exception;

/**
 * @author by zy.
 */
public class WrongPasswordException extends AppServiceException{

    public WrongPasswordException(String message) {
        super(message);
    }
}

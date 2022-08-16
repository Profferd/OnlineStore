package com.epam.hrushko.onlinestore.exceptions;

/**
 * Custom exception for services
 */
public class ServiceException extends Exception{

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

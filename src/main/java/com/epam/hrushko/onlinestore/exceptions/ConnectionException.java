package com.epam.hrushko.onlinestore.exceptions;

/**
 * Custom exception for connection
 */
public class ConnectionException extends Exception {

    public ConnectionException() {
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

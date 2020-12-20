package org.brytelabs.orm.exceptions;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DataAccessException(Throwable throwable) {
        this(throwable.getMessage(), throwable);
    }
}

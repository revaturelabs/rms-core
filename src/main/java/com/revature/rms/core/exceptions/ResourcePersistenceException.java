package com.revature.rms.core.exceptions;

public class ResourcePersistenceException extends RuntimeException {

    public ResourcePersistenceException() {
        super("An unspecified error occurred while trying to persist the resource.");
    }

    public ResourcePersistenceException(String s) {
        super(s);
    }

    public ResourcePersistenceException(String s, Throwable throwable) {
        super(s, throwable);
    }

}

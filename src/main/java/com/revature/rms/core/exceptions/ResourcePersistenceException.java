package com.revature.rms.core.exceptions;

/**
 * @author Wezley Singleton (GitHub: wsingleton)
 */
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

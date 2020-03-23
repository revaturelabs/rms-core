package com.revature.rms.core.exceptions;

/**
 * @author Wezley Singleton (GitHub: wsingleton)
 */
public class ResourceRetrievalException extends RuntimeException {

    public ResourceRetrievalException() {
        super("No resources found using the provided search criteria");
    }

    public ResourceRetrievalException(String s) {
        super(s);
    }

    public ResourceRetrievalException(String s, Throwable throwable) {
        super(s, throwable);
    }

}

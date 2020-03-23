package com.revature.rms.core.exceptions;

/**
 * @author Wezley Singleton (GitHub: wsingleton)
 */
public class NoImplementationException extends RuntimeException {

    public NoImplementationException() {
        super("The operation you are requesting is not currently implemented.");
    }

}

package com.revature.rms.core.exceptions;

public class NoImplementationException extends RuntimeException {

    public NoImplementationException() {
        super("The operation you are requesting is not currently implemented.");
    }

}

package com.revature.rms.core.exceptions;

public class EnumMappingException extends InvalidRequestException {

    public EnumMappingException() {
        super("Could not map enum using provided name");
    }

    public EnumMappingException(String s) {
        super(s);
    }

    public EnumMappingException(String s, Throwable throwable) {
        super(s, throwable);
    }

}

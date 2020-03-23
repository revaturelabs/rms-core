package com.revature.rms.core.exceptions;

/**
 * @author Wezley Singleton (GitHub: wsingleton)
 */
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

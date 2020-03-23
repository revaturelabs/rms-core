package com.revature.rms.core.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Serves as a basic implementation of a error response data transfer
 * object which is used to communicate granular information to a
 * requester regarding a failed request.
 */
public class ErrorResponse {

    /** HTTP status code numeric value */
    private int status;

    /** The timestamp of when this error response was created */
    private String timestamp;

    /** Granular information regarding a failed request */
    private String message;

    public ErrorResponse() {
        super();
    }

    /**
     * Creates an ErrorResponse instance by extracting HTTP status code info from the
     * HttpStatus object, and using the provided message string.
     *
     * @param status used to obtain status value
     * @param msg granular information regarding a failed request
     */
    public ErrorResponse(HttpStatus status, String msg) {
        this.status = status.value();
        this.timestamp = LocalDateTime.now().toString();
        this.message = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

}

package com.revature.rms.core.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    private int status;
    private String timestamp;
    private String message;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(HttpStatus status, Exception e) {
        this.status = status.value();
        this.timestamp = LocalDateTime.now().toString();
        this.message = e.getMessage();
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

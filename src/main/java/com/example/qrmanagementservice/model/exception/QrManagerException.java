package com.example.qrmanagementservice.model.exception;

public class QrManagerException extends RuntimeException {
    public QrManagerException(String message) {
        super(message);
    }

    public QrManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}

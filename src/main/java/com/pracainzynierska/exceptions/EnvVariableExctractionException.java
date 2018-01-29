package com.pracainzynierska.exceptions;

/**
 * Created by karol on 29.01.18.
 */
public class EnvVariableExctractionException extends Exception {
    public EnvVariableExctractionException() {
    }

    public EnvVariableExctractionException(String message) {
        super(message);
    }

    public EnvVariableExctractionException(String message, Throwable cause) {
        super(message, cause);
    }
}

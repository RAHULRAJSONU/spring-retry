package io.github.rahulrajsonu.springretry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends RuntimeException {

    private static String defaultMessage = "Service error";

    private String message;

    public ServiceException() {
        super(defaultMessage);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public final String getMessage() {
        return message != null ? message : defaultMessage;
    }
}

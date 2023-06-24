package com.trimix.personshandling.exception.api;

import com.trimix.personshandling.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = NotFoundException.MESSAGE)
public class NotFoundException extends ApiException {
    public static final transient String MESSAGE = "The resource does not exist.";
    @Serial
    private static final long serialVersionUID = 171136765138743740L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
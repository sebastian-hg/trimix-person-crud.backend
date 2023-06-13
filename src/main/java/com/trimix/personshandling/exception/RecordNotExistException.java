package com.trimix.personshandling.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotExistException extends Exception {
    private static final String MESSAGE = "the record haven't has been found in our dateBase";


    public RecordNotExistException() {
        super(MESSAGE);
    }
}

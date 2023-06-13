package com.trimix.personshandling.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotExistException extends Exception {
    private static final String MESSAGE = "Person not exist";


    public PersonNotExistException() {
        super(MESSAGE);
    }
}

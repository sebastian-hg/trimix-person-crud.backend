package com.trimix.personshandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PersonExistException extends Exception{

    private static final String MESSAGE = "Person exists";


    public PersonExistException() {
        super(MESSAGE);
    }
}


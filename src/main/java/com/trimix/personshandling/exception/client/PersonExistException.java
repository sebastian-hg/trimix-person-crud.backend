package com.trimix.personshandling.exception.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PersonExistException extends Exception{

    private static final String MESSAGE = "Person exists";


    public PersonExistException() {
        super(MESSAGE);
    }
}


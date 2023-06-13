package com.trimix.personshandling.service;

import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.request.AddPersonRequest;
import reactor.core.publisher.Mono;

public interface AddPersonService {

    Mono<Person> execute(AddPersonRequest person);
}

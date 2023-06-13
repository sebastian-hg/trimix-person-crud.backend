package com.trimix.personshandling.service;

import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.request.EditPersonRequest;
import reactor.core.publisher.Mono;

public interface EditPersonService {

    Mono<Person> execute(EditPersonRequest person, Long id);
}

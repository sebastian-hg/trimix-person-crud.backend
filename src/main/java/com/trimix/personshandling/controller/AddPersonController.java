package com.trimix.personshandling.controller;

import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.request.AddPersonRequest;
import com.trimix.personshandling.service.AddPersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AddPersonController {

    private final AddPersonService service;
    @CrossOrigin
    @PostMapping("/api/v1/person")
    public Mono<Person> createPerson(@RequestBody AddPersonRequest request) {
        return service.execute(request);
    }
}

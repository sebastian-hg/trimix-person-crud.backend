package com.trimix.personshandling.controller;

import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.request.EditPersonRequest;
import com.trimix.personshandling.service.EditPersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class EditPersonController {
    private final EditPersonService service;

    @CrossOrigin
    @PutMapping("/api/v1/person/editar")
    public Mono<Person> execute(@RequestParam Long id, @RequestBody EditPersonRequest request) {
        return service.execute(request, id);
    }
}

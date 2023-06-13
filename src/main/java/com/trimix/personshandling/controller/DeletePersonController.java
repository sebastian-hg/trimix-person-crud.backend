package com.trimix.personshandling.controller;

import com.trimix.personshandling.service.DeletePersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class DeletePersonController {
    private final DeletePersonService service;

    @CrossOrigin
    @DeleteMapping("/api/v1/person/delete")
    public Mono<Boolean> execute(@RequestParam Long id) {
        return service.execute(id);
    }
}

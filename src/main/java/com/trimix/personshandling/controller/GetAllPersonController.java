package com.trimix.personshandling.controller;

import com.trimix.personshandling.model.dto.response.PersonResponse;
import com.trimix.personshandling.service.GetAllPersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@RestController
public class GetAllPersonController {
    private GetAllPersonService service;

    @CrossOrigin
    @GetMapping("/api/v1/person")
    public Mono<List<PersonResponse>> execute(@RequestParam(name = "name", required = false) String filterName,
                                              @RequestParam(name = "documentType", required = false) String filterType) {
        return service.execute(filterName, filterType);
    }
}

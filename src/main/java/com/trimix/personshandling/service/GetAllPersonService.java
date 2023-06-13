package com.trimix.personshandling.service;

import com.trimix.personshandling.model.dto.response.PersonResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GetAllPersonService {
    Mono<List<PersonResponse>> execute(String filterName, String filterType);
}

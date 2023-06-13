package com.trimix.personshandling.service;

import reactor.core.publisher.Mono;

public interface DeletePersonService {

    Mono<Boolean> execute (Long id);
}

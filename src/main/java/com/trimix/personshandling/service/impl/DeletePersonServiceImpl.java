package com.trimix.personshandling.service.impl;

import com.trimix.personshandling.exception.PersonNotExistException;
import com.trimix.personshandling.repository.PersonRepository;
import com.trimix.personshandling.service.DeletePersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeletePersonServiceImpl implements DeletePersonService {

    private final PersonRepository personRepository;

    @Override
    public Mono<Boolean> execute(Long id) {
        return Mono.just(personRepository.findById(id))
                .filter(Optional::isPresent)
                .switchIfEmpty(Mono.error(PersonNotExistException::new))
                .map(Optional::get)
                .flatMap(person -> {
                    personRepository.delete(person);
                    return Mono.just(Boolean.TRUE);
                });
    }
}

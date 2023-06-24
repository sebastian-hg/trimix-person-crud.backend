package com.trimix.personshandling.service.impl;

import com.trimix.personshandling.exception.client.PersonNotExistException;
import com.trimix.personshandling.mapper.PersonMapper;
import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.request.EditPersonRequest;
import com.trimix.personshandling.repository.PersonRepository;
import com.trimix.personshandling.service.EditPersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@AllArgsConstructor
public class EditPersonServiceImpl implements EditPersonService {

    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    @Override
    public Mono<Person> execute(EditPersonRequest person, Long id) {

        return Mono.just(person)
                .zipWith(Mono.just(personRepository.existsById(id)))
                .filter(tuple2 -> Boolean.TRUE.equals(tuple2.getT2()))
                .switchIfEmpty(Mono.error(PersonNotExistException::new))
                .map(Tuple2::getT1)
                .map(mapper::ToDomain)
                .map( person1 -> {
                    person1.setId(id);
                    return personRepository.save(person1);
                });
    }
}

package com.trimix.personshandling.service.impl;

import com.trimix.personshandling.exception.PersonExistException;
import com.trimix.personshandling.mapper.PersonMapper;
import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.request.AddPersonRequest;
import com.trimix.personshandling.repository.PersonRepository;
import com.trimix.personshandling.service.AddPersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@AllArgsConstructor
public class AddPersonServiceImpl implements AddPersonService {

    private final PersonRepository personRepository;
    private final PersonMapper mapper;
    @Override
    public Mono<Person> execute(AddPersonRequest person) {

        return  Mono.just(person)
                .zipWith(Mono.just(personRepository.existsByDocumentNumberAndDocumentType(person.getDocumentNumber(),person.getDocumentType())))
                .filter(tuple2 ->  Boolean.FALSE.equals(tuple2.getT2()))
                .switchIfEmpty(Mono.error(PersonExistException::new))
                .map(Tuple2::getT1)
                .map(mapper::toDomain)
                .map(personRepository::save);
    }
}

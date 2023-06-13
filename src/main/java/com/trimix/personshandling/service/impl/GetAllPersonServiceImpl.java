package com.trimix.personshandling.service.impl;

import com.trimix.personshandling.mapper.PersonMapper;
import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.response.PersonResponse;
import com.trimix.personshandling.repository.PersonRepository;
import com.trimix.personshandling.service.GetAllPersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GetAllPersonServiceImpl implements GetAllPersonService {
    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    @Override
    public Mono<List<PersonResponse>> execute(String filterName, String filterType) {
        List<Person> alternative;
        if(filterName!="Nombre" && !Objects.equals(filterType, "null")){
            alternative = personRepository.findByDocumentTypeIgnoreCaseContainingAndNameIgnoreCaseContaining( filterType, filterName );
        } else if(filterName!=null){
            alternative=personRepository.findByNameIgnoreCaseContaining(filterName);
        } else {
            alternative=personRepository.findByDocumentTypeContaining(filterType);
        }
        
        return Mono.just(alternative)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.just(new ArrayList<>()));
    }
}

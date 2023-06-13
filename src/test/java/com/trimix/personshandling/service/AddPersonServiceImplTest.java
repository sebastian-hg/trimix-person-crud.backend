package com.trimix.personshandling.service;

import com.trimix.personshandling.mapper.PersonMapper;
import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.request.AddPersonRequest;
import com.trimix.personshandling.repository.PersonRepository;
import com.trimix.personshandling.service.impl.AddPersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class AddPersonServiceImplTest {
    @Mock
    private PersonRepository repository;
    @Mock
    private PersonMapper mapper;

    @InjectMocks
    private AddPersonServiceImpl service;

    private AddPersonRequest addPersonRequest;
    private Person personExpected;

    private Mono<Person> addPersonResponse;

    @Test
    void givenRequestWhenExecuteThenIsOk() {
        givenRequest();
        givenService();
        givenResponse();
        whenExecute();
        thenIsOk();
    }

    private void givenRequest() {
        addPersonRequest = AddPersonRequest.builder()
                .name("Sebastian")
                .surname("Hernandez")
                .documentNumber(957646791L)
                .documentType("Dni")
                .birthdate(LocalDate.now().minusDays(20L))
                .build();
    }

    private void givenService() {
        Person person = Person.builder()
                .id(1L)
                .name("Sebastian")
                .surname("Hernandez")
                .documentNumber(957646791L)
                .documentType("Dni")
                .birthdate(LocalDate.now().minusDays(20L))
                .build();

        Mockito.when(repository.existsByDocumentNumberAndDocumentType(any(Long.class), anyString())).thenReturn(Boolean.FALSE);
        Mockito.when(mapper.toDomain(addPersonRequest)).thenReturn(person);
        Mockito.when(repository.save(any(Person.class))).thenReturn(person);
    }

    private void givenResponse() {
        personExpected = Person.builder()
                .id(1L)
                .name("Sebastian")
                .surname("Hernandez")
                .documentNumber(957646791L)
                .documentType("Dni")
                .birthdate(LocalDate.now().minusDays(20L))
                .build();
    }

    private void whenExecute() {
        addPersonResponse = service.execute(addPersonRequest);
    }

    private void thenIsOk() {
        StepVerifier.create(addPersonResponse)
                .expectNextMatches(person1 -> person1.equals(personExpected))
                .expectComplete()
                .verify();
        Mockito.verify(repository).existsByDocumentNumberAndDocumentType(any(Long.class), any(String.class));
        Mockito.verify(repository).save(any(Person.class));
        Mockito.verify(mapper).toDomain(any(AddPersonRequest.class));
    }
}

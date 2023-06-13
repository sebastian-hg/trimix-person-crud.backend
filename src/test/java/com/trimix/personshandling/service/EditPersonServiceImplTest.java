package com.trimix.personshandling.service;

import com.trimix.personshandling.mapper.PersonMapper;
import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.request.EditPersonRequest;
import com.trimix.personshandling.repository.PersonRepository;
import com.trimix.personshandling.service.impl.EditPersonServiceImpl;
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

@ExtendWith(SpringExtension.class)
public class EditPersonServiceImplTest {

    @Mock
    private PersonRepository repository;
    @Mock
    private PersonMapper mapper;

    @InjectMocks
    private EditPersonServiceImpl service;

    private EditPersonRequest request;

    private Long idRequest;


    private Person expected;

    private Person person;

    private Mono<Person> response;

    @Test
    void givenRequestWhenExecuteThemIsOg() {
        givenRequest();
        givenService();
        givenResponse();
        whenExecute();
        thenIsOk();
    }

    private void givenRequest() {
        request = EditPersonRequest.builder()
                .name("Jose")
                .surname("Smith")
                .documentNumber(654325262L)
                .documentType("Pasaporte")
                .birthdate(LocalDate.now().minusDays(20L))
                .build();

        idRequest = 1L;
    }

    private void givenService() {
        var person = Person.builder()
                .id(1L)
                .name("Jose")
                .surname("Smith")
                .documentNumber(54325262L)
                .documentType("Pasaporte")
                .birthdate(LocalDate.now().minusDays(20L))
                .build();

        Mockito.when(repository.existsById(any(Long.class))).thenReturn(Boolean.TRUE);
        Mockito.when(mapper.ToDomain(request)).thenReturn(person);
        Mockito.when(repository.save(any(Person.class))).thenReturn(person);
    }

    private void givenResponse() {
        expected = Person.builder()
                .id(1L)
                .name("Jose")
                .surname("Smith")
                .documentNumber(54325262L)
                .documentType("Pasaporte")
                .birthdate(LocalDate.now().minusDays(20L))
                .build();
    }

    private void whenExecute() {
        response = service.execute(request, idRequest);
    }

    private void thenIsOk() {
        StepVerifier.create(response)
                .expectNextMatches(person1 -> person1.equals(expected))
                .expectComplete()
                .verify();
        Mockito.verify(repository).existsById(any(Long.class));
        Mockito.verify(mapper).ToDomain(any(EditPersonRequest.class));
        Mockito.verify(repository).save(any(Person.class));
    }
}

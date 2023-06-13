package com.trimix.personshandling.service;

import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.repository.PersonRepository;
import com.trimix.personshandling.service.impl.DeletePersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class DeletePersonServiceImplTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private DeletePersonServiceImpl service;

    private Long idRequest;
    private Boolean expected;
    private Mono<Boolean> response;

    private Person person;

    @Test
    void givenRequestWhenExecuteThemIsOg() {
        givenRequest();
        givenService();
        givenResponse();
        whenExecute();
        thenIsOk();
    }

    private void givenRequest() {
        idRequest = 1L;
        person = Person.builder()
                .id(1L)
                .name("Sebastian")
                .surname("Hernandez")
                .documentNumber(95764679L)
                .documentType("Dni")
                .birthdate(LocalDate.now().minusDays(20L))
                .build();
    }

    private void givenService() {

        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(person));
        Mockito.doNothing().when(repository).delete(person);
    }

    private void givenResponse() {
        expected=Boolean.TRUE;
    }

    private void whenExecute() {
        response = service.execute(idRequest);
    }

    private void thenIsOk() {
        StepVerifier.create(response)
                .expectNextMatches(response1 -> response1.equals(expected))
                .expectComplete()
                .verify();
        Mockito.verify(repository).findById(any(Long.class));
        Mockito.verify(repository).delete(person);
    }

}

package com.trimix.personshandling.service;

import com.trimix.personshandling.mapper.PersonMapper;
import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.request.GetPersonByNameRequest;
import com.trimix.personshandling.model.dto.response.PersonResponse;
import com.trimix.personshandling.repository.PersonRepository;
import com.trimix.personshandling.service.impl.GetAllPersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class GetAllPersonServiceImplTest {

    @Mock
    private PersonRepository repository;
    @Mock
    private PersonMapper mapper;

    @InjectMocks
    private GetAllPersonServiceImpl service;

    private String nameRequest;
    private String FilterRequest;
    private final List<PersonResponse> listExpected = new ArrayList<>();
    private Mono<List<PersonResponse>> monoResponse;

    @Test
    void givenRequestWhenExecuteThemIsOg() {
        givenRequest();
        givenService();
        givenResponse();
        whenExecute();
        thenIsOk();
    }

    private void givenRequest() {
        nameRequest = "sebastian";
        FilterRequest = null;
    }

    private void givenService() {
        var person = Person.builder()
                .id(1L)
                .name("Sebastian")
                .surname("Hernandez")
                .documentNumber(25671955L)
                .documentType("Dni")
                .birthdate(LocalDate.now().minusDays(20L))
                .build();
        List<Person> personList = new ArrayList<Person>();
        personList.add(person);
        var person1 = PersonResponse.builder()
                .id(1L)
                .name("Sebastian")
                .surname("Hernandez")
                .documentNumber(25671955L)
                .documentType("Dni")
                .birthdate(LocalDate.now().minusDays(20L))
                .build();
        List<PersonResponse> responseList = new ArrayList<PersonResponse>();
        responseList.add(person1);

        Mockito.when(repository.findByNameIgnoreCaseContaining(any(String.class))).thenReturn(personList);
        Mockito.when(mapper.toDTO(personList)).thenReturn(responseList);
    }

    private void givenResponse() {
        var person1 = PersonResponse.builder()
                .id(1L)
                .name("Sebastian")
                .surname("Hernandez")
                .documentNumber(25671955L)
                .documentType("Dni")
                .birthdate(LocalDate.now().minusDays(20L))
                .build();
        List<PersonResponse> responseList = new ArrayList<PersonResponse>();
        listExpected.add(person1);
    }

    private void whenExecute() {
        monoResponse = service.execute(nameRequest ,FilterRequest);
    }

    private void thenIsOk() {
        StepVerifier.create(monoResponse)
                .expectNextMatches(person1 -> person1.equals(listExpected))
                .expectComplete()
                .verify();
        Mockito.verify(repository).findByNameIgnoreCaseContaining(any(String.class));
        Mockito.verify(mapper).toDTO(any());
    }
}

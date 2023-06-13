package com.trimix.personshandling.mapper;

import com.trimix.personshandling.model.Person;
import com.trimix.personshandling.model.dto.request.AddPersonRequest;
import com.trimix.personshandling.model.dto.request.EditPersonRequest;
import com.trimix.personshandling.model.dto.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    //@Mapping(target = "birthdate", expression = "java(java.time.LocalDate.parse(requestDTO.getBirthdate().toString()))")
    Person toDomain(AddPersonRequest requestDTO);

    Person ToDomain(EditPersonRequest editPersonRequestDTO);

    List<PersonResponse> toDTO(List<Person> personList);
}



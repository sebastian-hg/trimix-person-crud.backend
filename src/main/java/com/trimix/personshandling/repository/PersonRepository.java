package com.trimix.personshandling.repository;

import com.trimix.personshandling.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Boolean existsByDocumentNumberAndDocumentType(Long documentNumber, String typeDocument);

    List<Person> findByNameContaining(String name);

    List<Person> findByNameIgnoreCaseContaining(String name);

    List<Person> findByDocumentTypeContaining(String documentType);

    List<Person> findByDocumentTypeIgnoreCaseContainingAndNameIgnoreCaseContaining(String documentType, String name);
}

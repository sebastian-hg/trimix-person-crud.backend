package com.trimix.personshandling.model.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
public class AddPersonRequest {
    private String name;
    private String surname;
    private Long documentNumber;
    private String documentType;
    private LocalDate birthdate;
}

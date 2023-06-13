package com.trimix.personshandling.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditPersonRequest {
    private String name;
    private String surname;
    private Long documentNumber;
    private String documentType;
    private LocalDate birthdate;
}
package com.zadatak.zadatak.dto;

import jakarta.validation.Valid;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FormularPopunjenDTO {
    private int id;
    private int formularId;
    @Valid
    List<PoljePopunjenoDTO> popunjenaPolja;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;
}

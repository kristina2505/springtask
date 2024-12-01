package com.zadatak.zadatak.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FormularPopunjenDTO {
    private int id;
    private int formularId;
    List<PoljePopunjenoDTO> popunjenaPolja;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;
}

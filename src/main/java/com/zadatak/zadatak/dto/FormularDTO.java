package com.zadatak.zadatak.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FormularDTO {
    private int id;

    @NotNull(message = "Naziv formulara je obavezan.")
    @Size(min = 3, max = 256, message = "Naziv formulara mora imati između 3 i 256 karaktera.")
    private String naziv;

    @Valid
    private List<PoljeDTO> polja;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;

}

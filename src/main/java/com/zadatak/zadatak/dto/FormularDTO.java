package com.zadatak.zadatak.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FormularDTO {
    private int id;
    private String naziv;
    private List<PoljeDTO> polja;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;

}

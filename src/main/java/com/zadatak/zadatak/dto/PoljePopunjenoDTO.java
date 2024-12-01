package com.zadatak.zadatak.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PoljePopunjenoDTO {
    private int id;
    private int formularPopunjenId;
    private int poljeId;
    private String vrednostTekst;
    private double vrednostBroj;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;

}

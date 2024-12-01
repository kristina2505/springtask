package com.zadatak.zadatak.dto;

import com.zadatak.zadatak.model.TipPolja;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PoljeDTO {
    private int id;
    private int formularId;
    private String naziv;
    private int prikazniRedosled;
    private TipPolja tipPolja;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;
}

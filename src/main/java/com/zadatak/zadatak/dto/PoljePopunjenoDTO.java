package com.zadatak.zadatak.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Digits;
import lombok.Data;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;

@Data
public class PoljePopunjenoDTO {
    private int id;
    private int formularPopunjenId;
    private int poljeId;

    @Size(max = 256, message = "Tekstualna vrednost ne sme biti duza od 256 karaktera.")
    private String vrednostTekst;

    private Double vrednostBroj;

    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;

    @AssertTrue(message = "Jedna od vrednosti (tekstualna ili numerička) mora biti postavljena.")
    private boolean isOneValueSet() {
        return (vrednostTekst != null && !vrednostTekst.isEmpty()) || vrednostBroj != null;
    }
}

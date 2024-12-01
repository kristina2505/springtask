package com.zadatak.zadatak.dto;

import com.zadatak.zadatak.model.TipPolja;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PoljeDTO {
    private int id;
    private int formularId;

    @NotNull(message = "Naziv polja je obavezan.")
    @Size(min = 1, max = 256, message = "Naziv polja mora imati izmedju 1 i 256 karaktera.")
    private String naziv;

    @NotNull(message = "Prikazni redosled je obavezan.")
    @Min(value = 1, message = "Prikazni redosled mora biti 1 ili veci.")
    private int prikazniRedosled;

    @NotNull(message = "Tip polja je obavezan.")
    private TipPolja tipPolja;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;
}

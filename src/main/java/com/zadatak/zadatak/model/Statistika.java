package com.zadatak.zadatak.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Table(name = "Statistika")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Statistika {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate datum;

    @Column(nullable = false)
    private int brojPopunjenihFormulara;
}

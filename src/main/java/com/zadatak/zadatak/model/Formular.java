package com.zadatak.zadatak.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "Formular")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Formular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String naziv;

    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;

    @PrePersist
    protected void onCreate() {
        this.vremeKreiranja = LocalDateTime.now();
        this.vremePoslednjeIzmene = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.vremePoslednjeIzmene = LocalDateTime.now();
    }
}

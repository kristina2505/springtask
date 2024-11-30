package com.zadatak.zadatak.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "Polje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Polje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formular_id", nullable = false)
    private Formular formular;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private int prikazniRedosled;

    @Enumerated(EnumType.STRING)
    private TipPolja tipPolja;

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

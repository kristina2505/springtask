package com.zadatak.zadatak.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "FormularPopunjen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FormularPopunjen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "formular_id", nullable = false)
    private Formular formular;

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

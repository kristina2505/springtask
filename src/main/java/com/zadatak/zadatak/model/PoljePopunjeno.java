package com.zadatak.zadatak.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Table(name = "PoljePopunjeno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PoljePopunjeno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "formular_popunjen_id", nullable = false)
    private FormularPopunjen formularPopunjen;

    @ManyToOne
    @JoinColumn(name = "polje_id")
    private Polje polje;

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

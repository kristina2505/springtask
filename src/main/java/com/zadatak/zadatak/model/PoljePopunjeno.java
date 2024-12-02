package com.zadatak.zadatak.model;

import com.zadatak.zadatak.service.KorisnikContextService;
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
    private int id;

    @ManyToOne
    @JoinColumn(name = "formular_popunjen_id", nullable = false)
    private FormularPopunjen formularPopunjen;

    @ManyToOne
    @JoinColumn(name = "polje_id")
    private Polje polje;

    private String vrednostTekst;
    private double vrednostBroj;

    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnik_kreirao", nullable = false)
    private Korisnik korisnikKreirao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnik_poslednji_azurirao", nullable = true)
    private Korisnik korisnikPoslednjiAzurirao;

    @Transient
    private static KorisnikContextService korisnikContextService;

    public static void setKorisnikContextService(KorisnikContextService service) {
        korisnikContextService = service;
    }

    @PrePersist
    protected void onCreate() {
        this.vremeKreiranja = LocalDateTime.now();
        this.vremePoslednjeIzmene = LocalDateTime.now();
        this.korisnikKreirao = korisnikContextService.getCurrentUser();
        this.korisnikPoslednjiAzurirao = korisnikContextService.getCurrentUser();
    }

    @PreUpdate
    protected void onUpdate() {
        this.vremePoslednjeIzmene = LocalDateTime.now();
        this.korisnikPoslednjiAzurirao = korisnikContextService.getCurrentUser();
    }
}

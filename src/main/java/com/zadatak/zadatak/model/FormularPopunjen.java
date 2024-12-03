package com.zadatak.zadatak.model;

import com.zadatak.zadatak.service.KorisnikContextService;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Table(name = "FormularPopunjen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FormularPopunjen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "formular_id", nullable = false)
    private Formular formular;

    @OneToMany(mappedBy = "formularPopunjen", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PoljePopunjeno> popunjenaPolja;

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

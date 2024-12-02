package com.zadatak.zadatak.config;

import com.zadatak.zadatak.model.Formular;
import com.zadatak.zadatak.model.FormularPopunjen;
import com.zadatak.zadatak.model.Polje;
import com.zadatak.zadatak.model.PoljePopunjeno;
import com.zadatak.zadatak.service.KorisnikContextService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KorisnikContextConfig {
    private final KorisnikContextService korisnikContextService;

    @PostConstruct
    public void init() {
        Formular.setKorisnikContextService(korisnikContextService);
        Polje.setKorisnikContextService(korisnikContextService);
        FormularPopunjen.setKorisnikContextService(korisnikContextService);
        PoljePopunjeno.setKorisnikContextService(korisnikContextService);
    }
}

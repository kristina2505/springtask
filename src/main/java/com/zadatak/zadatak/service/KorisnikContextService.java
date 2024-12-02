package com.zadatak.zadatak.service;

import com.zadatak.zadatak.model.Korisnik;
import com.zadatak.zadatak.security.KorisnikDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class KorisnikContextService {
    public Korisnik getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            KorisnikDetails korisnikDetails = (KorisnikDetails) authentication.getPrincipal();
            return korisnikDetails.getKorisnik();
        }
        throw new EntityNotFoundException("Korisnik nije pronadjen");
    }
}


package com.zadatak.zadatak.service;

import com.zadatak.zadatak.model.Korisnik;
import com.zadatak.zadatak.repository.KorisnikRepository;
import com.zadatak.zadatak.security.KorisnikDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KorisnikDetailsService implements UserDetailsService {

    private final KorisnikRepository korisnikRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(username)
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik nije pronađen sa korisničkim imenom: " + username));
        return new KorisnikDetails(korisnik);
    }
}

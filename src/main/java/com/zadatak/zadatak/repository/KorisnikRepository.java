package com.zadatak.zadatak.repository;

import com.zadatak.zadatak.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
}

package com.zadatak.zadatak.repository;

import com.zadatak.zadatak.model.Statistika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistikaRepository extends JpaRepository<Statistika, Integer> {
}

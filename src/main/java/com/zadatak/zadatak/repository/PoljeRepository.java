package com.zadatak.zadatak.repository;

import com.zadatak.zadatak.model.Polje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoljeRepository extends JpaRepository<Polje, Integer> {
    List<Polje> findAllByFormular_Id(int formularId);
}

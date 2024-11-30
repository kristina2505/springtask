package com.zadatak.zadatak.repository;

import com.zadatak.zadatak.model.PoljePopunjeno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoljePopunjenoRepository extends JpaRepository<PoljePopunjeno, Integer> {
    List<PoljePopunjeno> findAllByFormularPopunjen_Id(int formularPopunjenId);
}

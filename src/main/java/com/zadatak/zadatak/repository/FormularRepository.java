package com.zadatak.zadatak.repository;

import com.zadatak.zadatak.model.Formular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularRepository extends JpaRepository<Formular, Integer> {
}

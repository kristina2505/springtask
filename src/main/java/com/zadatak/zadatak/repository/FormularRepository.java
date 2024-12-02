package com.zadatak.zadatak.repository;

import com.zadatak.zadatak.model.Formular;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularRepository extends JpaRepository<Formular, Integer> {
    Page<Formular> findAll(Pageable pageable);
}

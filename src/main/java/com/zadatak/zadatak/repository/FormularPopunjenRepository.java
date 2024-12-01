package com.zadatak.zadatak.repository;

import com.zadatak.zadatak.model.FormularPopunjen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularPopunjenRepository extends JpaRepository<FormularPopunjen, Integer> {
}

package com.zadatak.zadatak.repository;

import com.zadatak.zadatak.model.FormularPopunjen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FormularPopunjenRepository extends JpaRepository<FormularPopunjen, Integer> {
    List<FormularPopunjen> findByVremeKreiranjaBetween(LocalDateTime startOfYesterday, LocalDateTime endOfYesterday);
}

package com.zadatak.zadatak.controller;

import com.zadatak.zadatak.dto.PoljeDTO;
import com.zadatak.zadatak.service.PoljeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polje")
@AllArgsConstructor
public class PoljeController {

    private final PoljeService poljeService;

    @GetMapping
    @Operation(summary = "Get all polja", description = "Dohvati sva polja")
    public ResponseEntity<List<PoljeDTO>> getAll() {
        return ResponseEntity.ok(poljeService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get polje by ID", description = "Dohvati polje na osnovu njegovog ID-a")
    public ResponseEntity<PoljeDTO> getById(@PathVariable int id) {
        try{
            return poljeService.getById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

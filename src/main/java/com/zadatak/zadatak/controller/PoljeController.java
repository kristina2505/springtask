package com.zadatak.zadatak.controller;

import com.zadatak.zadatak.dto.FormularDTO;
import com.zadatak.zadatak.dto.PoljeDTO;
import com.zadatak.zadatak.model.Formular;
import com.zadatak.zadatak.service.FormularService;
import com.zadatak.zadatak.service.PoljeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/polja")
@AllArgsConstructor
public class PoljeController {

    private final PoljeService poljeService;
    private final FormularService formularService;

    @GetMapping
    public ResponseEntity<List<PoljeDTO>> getAll() {
        return ResponseEntity.ok(poljeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoljeDTO> getById(@PathVariable int id) {
        try{
            return poljeService.getById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<PoljeDTO> create(@RequestBody PoljeDTO poljeDTO) {
        try {
            Optional<FormularDTO> formularDTOOptional = formularService.getFormularById(poljeDTO.getFormularId());
            if (formularDTOOptional.isPresent()) {
                return ResponseEntity.ok(poljeService.createPolje(poljeDTO, formularDTOOptional.get()).orElseThrow());
            }return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoljeDTO> update(@PathVariable int id, @RequestBody PoljeDTO poljeDTO) {
        try {
            poljeDTO.setId(id);
            return ResponseEntity.ok(poljeService.updatePolje(poljeDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        try {
            PoljeDTO poljeDTO = new PoljeDTO();
            poljeDTO.setId(id);
            if (poljeService.deletePolje(poljeDTO)) {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

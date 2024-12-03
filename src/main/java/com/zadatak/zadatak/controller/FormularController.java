package com.zadatak.zadatak.controller;

import com.zadatak.zadatak.dto.FormularDTO;
import com.zadatak.zadatak.dto.PoljeDTO;
import com.zadatak.zadatak.service.FormularService;
import com.zadatak.zadatak.service.PoljeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/formular")
public class FormularController {
    private final FormularService formularService;
    private final PoljeService poljeService;

    @GetMapping
    @Operation(summary = "Get all formulari", description = "Dohvati sve formulare")
    public ResponseEntity<Page<FormularDTO>> getall(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(formularService.getAll(page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get formular by ID", description = "Dohvati formular na osnovu njegovog ID-a")
    public ResponseEntity<FormularDTO> getFormularById(@PathVariable int id) {
        try {
            return formularService.getFormularById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Save formular", description = "Sacuvaj novi formular")
    public ResponseEntity<FormularDTO> saveFormular(@Valid @RequestBody FormularDTO formularDTO) {
        try {
            return ResponseEntity.ok(formularService.createFormular(formularDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update formular", description = "Azuriraj formular")
    public ResponseEntity<FormularDTO> updateFormular(@PathVariable int id, @Valid @RequestBody FormularDTO formularDTO) {
        try {
            formularDTO.setId(id);
            return ResponseEntity.ok(formularService.updateFormular(formularDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete formular", description = "Obrisi formular")
    public ResponseEntity<Void> deleteFormular(@PathVariable int id) {
        try {
            FormularDTO formularDTO = new FormularDTO();
            formularDTO.setId(id);
            if (formularService.deleteFormular(formularDTO)) {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/polje")
    @Operation(summary = "Add polje", description = "Dodaj novo polje u formular")
    public ResponseEntity<PoljeDTO> createPolje(@Valid @RequestBody PoljeDTO poljeDTO) {
        try {
            Optional<FormularDTO> formularDTOOptional = formularService.getFormularById(poljeDTO.getFormularId());
            if (formularDTOOptional.isPresent()) {
                return ResponseEntity.ok(poljeService.createPolje(poljeDTO, formularDTOOptional.get()).orElseThrow());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/polje/{id}")
    @Operation(summary = "Update polje", description = "Azuriraj polje")
    public ResponseEntity<PoljeDTO> updatePolje(@PathVariable int id, @Valid @RequestBody PoljeDTO poljeDTO) {
        try {
            poljeDTO.setId(id);
            return ResponseEntity.ok(poljeService.updatePolje(poljeDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("polje/{id}")
    @Operation(summary = "Delete polje", description = "Obrisi polje")
    public ResponseEntity<Void> deletePolje(@PathVariable int id) {
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

package com.zadatak.zadatak.controller;

import com.zadatak.zadatak.dto.FormularDTO;
import com.zadatak.zadatak.dto.PoljeDTO;
import com.zadatak.zadatak.service.FormularService;
import com.zadatak.zadatak.service.PoljeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<FormularDTO>> getall() {
        return ResponseEntity.ok(formularService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormularDTO> getFormularById(@PathVariable int id) {
        try{
            return formularService.getFormularById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FormularDTO> saveFormular(@Valid @RequestBody FormularDTO formularDTO) {
        try{
            return ResponseEntity.ok(formularService.createFormular(formularDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormularDTO> updateFormular(@PathVariable int id, @Valid @RequestBody FormularDTO formularDTO) {
        try {
            formularDTO.setId(id);
            return ResponseEntity.ok(formularService.updateFormular(formularDTO).orElseThrow());
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
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
    public ResponseEntity<PoljeDTO> createPolje(@Valid @RequestBody PoljeDTO poljeDTO) {
        try {
            Optional<FormularDTO> formularDTOOptional = formularService.getFormularById(poljeDTO.getFormularId());
            if (formularDTOOptional.isPresent()) {
                return ResponseEntity.ok(poljeService.createPolje(poljeDTO, formularDTOOptional.get()).orElseThrow());
            }return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/polje/{id}")
    public ResponseEntity<PoljeDTO> updatePolje(@PathVariable int id,@Valid @RequestBody PoljeDTO poljeDTO) {
        try {
            poljeDTO.setId(id);
            return ResponseEntity.ok(poljeService.updatePolje(poljeDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("polje/{id}")
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

package com.zadatak.zadatak.controller;

import com.zadatak.zadatak.dto.FormularDTO;
import com.zadatak.zadatak.service.FormularService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/formular")
public class FormularController {
    private final FormularService formularService;

    @GetMapping
    public ResponseEntity<List<FormularDTO>> getall() {
        return ResponseEntity.ok(formularService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormularDTO> getById(@PathVariable int id) {
        try{
            return formularService.getFormularById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FormularDTO> save(@RequestBody FormularDTO formularDTO) {
        try{
            return ResponseEntity.ok(formularService.createFormular(formularDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormularDTO> update(@PathVariable int id, @RequestBody FormularDTO formularDTO) {
        try {
            formularDTO.setId(id);
            return ResponseEntity.ok(formularService.updateFormular(formularDTO).orElseThrow());
        }catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
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
}

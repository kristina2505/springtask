package com.zadatak.zadatak.controller;

import com.zadatak.zadatak.dto.FormularPopunjenDTO;
import com.zadatak.zadatak.service.FormularPopunjenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formular-popunjen")
@RequiredArgsConstructor
public class FormularPopunjenController {
    private final FormularPopunjenService formularPopunjenService;

    @GetMapping
    public ResponseEntity<List<FormularPopunjenDTO>> getAll() {
        return ResponseEntity.ok(formularPopunjenService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormularPopunjenDTO> getById(@PathVariable int id) throws Exception {
        try{
            return formularPopunjenService.getById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FormularPopunjenDTO> create(@RequestBody FormularPopunjenDTO formularPopunjenDTO) throws Exception {
        try{
            return ResponseEntity.ok(formularPopunjenService.save(formularPopunjenDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormularPopunjenDTO> update(@PathVariable int id, @RequestBody FormularPopunjenDTO formularPopunjenDTO) {
        try {
            formularPopunjenDTO.setId(id);
            return ResponseEntity.ok(formularPopunjenService.update(formularPopunjenDTO).orElseThrow());
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws Exception {
        try {
            FormularPopunjenDTO formularPopunjenDTO = new FormularPopunjenDTO();
            formularPopunjenDTO.setId(id);
            if (formularPopunjenService.delete(formularPopunjenDTO)) {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

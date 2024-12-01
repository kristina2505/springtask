package com.zadatak.zadatak.controller;

import com.zadatak.zadatak.dto.FormularPopunjenDTO;
import com.zadatak.zadatak.dto.PoljePopunjenoDTO;
import com.zadatak.zadatak.service.FormularPopunjenService;
import com.zadatak.zadatak.service.PoljePopunjenoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formular-popunjen")
@RequiredArgsConstructor
public class FormularPopunjenController {
    private final FormularPopunjenService formularPopunjenService;
    private final PoljePopunjenoService poljePopunjenoService;

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
    public ResponseEntity<FormularPopunjenDTO> createFormularPopunjen(@Valid @RequestBody FormularPopunjenDTO formularPopunjenDTO) throws Exception {
        try{
            return ResponseEntity.ok(formularPopunjenService.save(formularPopunjenDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormularPopunjenDTO> updateFormularPopunjen(@PathVariable int id, @Valid @RequestBody FormularPopunjenDTO formularPopunjenDTO) {
        try {
            formularPopunjenDTO.setId(id);
            return ResponseEntity.ok(formularPopunjenService.update(formularPopunjenDTO).orElseThrow());
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormularPopunjen(@PathVariable int id) throws Exception {
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

    @PostMapping("/polje-popunjeno")
    public ResponseEntity<PoljePopunjenoDTO> createPoljePopunjeno(@Valid @RequestBody PoljePopunjenoDTO poljePopunjenoDTO) throws Exception {
        try {
            Optional<FormularPopunjenDTO> formularPopunjenDTO = formularPopunjenService.getById(poljePopunjenoDTO.getFormularPopunjenId());
            if (formularPopunjenDTO.isPresent()) {
                return ResponseEntity.ok(poljePopunjenoService.createPoljePopunjeno(poljePopunjenoDTO, formularPopunjenDTO.get()).orElseThrow());
            }return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/polje-popunjeno/{id}")
    public ResponseEntity<PoljePopunjenoDTO> updatePoljePopunjeno( @PathVariable int id, @Valid @RequestBody PoljePopunjenoDTO poljePopunjenoDTO) throws Exception {
        try {
            poljePopunjenoDTO.setId(id);
            return ResponseEntity.ok(poljePopunjenoService.updatePoljePopunjeno(poljePopunjenoDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/polje-popunjeno/{id}")
    public ResponseEntity<Void> deletePoljePopunjeno(@PathVariable int id) throws Exception {
        try {
            PoljePopunjenoDTO poljePopunjenoDTO = new PoljePopunjenoDTO();
            poljePopunjenoDTO.setId(id);
            if (poljePopunjenoService.deletePoljePopunjeno(poljePopunjenoDTO)) {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

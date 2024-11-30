package com.zadatak.zadatak.controller;

import com.zadatak.zadatak.dto.PoljePopunjenoDTO;
import com.zadatak.zadatak.service.PoljePopunjenoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polje-popunjeno")
@RequiredArgsConstructor
public class PoljePopunjenoController {
    private final PoljePopunjenoService poljePopunjenoService;

    @GetMapping
    public ResponseEntity<List<PoljePopunjenoDTO>> getAll() {
        return ResponseEntity.ok(poljePopunjenoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoljePopunjenoDTO> getById(@PathVariable int id) throws Exception {
        try{
            return poljePopunjenoService.getById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<PoljePopunjenoDTO> create(@RequestBody PoljePopunjenoDTO poljePopunjenoDTO) throws Exception {
        try {
            //todo
            return ResponseEntity.ok(poljePopunjenoService.createPoljePopunjeno(poljePopunjenoDTO, null).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoljePopunjenoDTO> update(@PathVariable int id, @RequestBody PoljePopunjenoDTO poljePopunjenoDTO) throws Exception {
        try {
            poljePopunjenoDTO.setId(id);
            return ResponseEntity.ok(poljePopunjenoService.updatePoljePopunjeno(poljePopunjenoDTO).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws Exception {
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

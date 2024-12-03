package com.zadatak.zadatak.controller;

import com.zadatak.zadatak.dto.PoljePopunjenoDTO;
import com.zadatak.zadatak.service.FormularPopunjenService;
import com.zadatak.zadatak.service.PoljePopunjenoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polje-popunjeno")
@RequiredArgsConstructor
public class PoljePopunjenoController {
    private final PoljePopunjenoService poljePopunjenoService;
    private final FormularPopunjenService formularPopunjenService;

    @GetMapping
    @Operation(summary = "Get all popunjena polja", description = "Dohvati sva popunjena polja")
    public ResponseEntity<List<PoljePopunjenoDTO>> getAll() {
        return ResponseEntity.ok(poljePopunjenoService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get popunjeno polje by ID", description = "Dohvati popunjeno polje na osnovu njegovog ID-a")
    public ResponseEntity<PoljePopunjenoDTO> getById(@PathVariable int id) throws Exception {
        try{
            return poljePopunjenoService.getById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

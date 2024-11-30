package com.zadatak.zadatak.service;

import com.zadatak.zadatak.dto.FormularDTO;
import com.zadatak.zadatak.dto.PoljeDTO;
import com.zadatak.zadatak.mapper.FormularMapper;
import com.zadatak.zadatak.mapper.PoljeMapper;
import com.zadatak.zadatak.model.Formular;
import com.zadatak.zadatak.model.Polje;
import com.zadatak.zadatak.repository.FormularRepository;
import com.zadatak.zadatak.repository.PoljeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class PoljeService {
    private final PoljeRepository poljeRepository;
    private final PoljeMapper poljeMapper;
    private final FormularMapper formularMapper;

    public List<PoljeDTO> getAll() {
        List<Polje> polja = poljeRepository.findAll();

        List<PoljeDTO> poljeDTOs = new ArrayList<>();
        for (Polje polje : polja) {
            PoljeDTO poljeDTO = poljeMapper.toPoljeDTO(polje);
            poljeDTOs.add(poljeDTO);
        }
        return poljeDTOs;
    }

    public Optional<PoljeDTO> getById(int id) throws Exception {
        Optional<Polje> polje = poljeRepository.findById(id);
        if (polje.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjeno polje za zadati ID");
        }
        return Optional.of(poljeMapper.toPoljeDTO(polje.get()));
    }

    public Optional<PoljeDTO> createPolje(PoljeDTO poljeDTO, FormularDTO formularDTO) throws Exception {
        Formular formular = formularMapper.toFormular(formularDTO);
        Polje polje = poljeMapper.toPolje(poljeDTO);
        polje.setFormular(formular);
        Polje savedPolje = poljeRepository.save(polje);
        return Optional.of(poljeMapper.toPoljeDTO(savedPolje));
    }

    public Optional<PoljeDTO> updatePolje(PoljeDTO poljeDTO) throws Exception {
        Optional<Polje> poljeOptional = poljeRepository.findById(poljeDTO.getId());
        if (poljeOptional.isEmpty()) {
            throw new EntityNotFoundException("Ne postoji polje za zadati ID");
        }
        Polje polje = poljeOptional.get();
        poljeMapper.updatePoljeFromDTO(poljeDTO, polje);
        Polje updatedPolje = poljeRepository.save(polje);
        return Optional.of(poljeMapper.toPoljeDTO(updatedPolje));
    }

    public boolean deletePolje(PoljeDTO poljeDTO) throws Exception {
        Optional<Polje> poljeOptional = poljeRepository.findById(poljeDTO.getId());
        if (poljeOptional.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjeno polje za zadati ID");
        }
        poljeRepository.deleteById(poljeDTO.getId());
        return true;
    }

    public List<PoljeDTO> getByFormularId(int formularId) {
        List<Polje> polja = poljeRepository.findAllByFormular_Id(formularId);
        List<PoljeDTO> poljeDTOs = new ArrayList<>();
        for (Polje polje : polja) {
            PoljeDTO poljeDTO = poljeMapper.toPoljeDTO(polje);
            poljeDTOs.add(poljeDTO);
        }
        return poljeDTOs;
    }

}

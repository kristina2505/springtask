package com.zadatak.zadatak.service;

import com.zadatak.zadatak.dto.FormularDTO;
import com.zadatak.zadatak.dto.FormularPopunjenDTO;
import com.zadatak.zadatak.dto.PoljeDTO;
import com.zadatak.zadatak.dto.PoljePopunjenoDTO;
import com.zadatak.zadatak.mapper.FormularMapper;
import com.zadatak.zadatak.mapper.FormularPopunjenMapper;
import com.zadatak.zadatak.mapper.PoljeMapper;
import com.zadatak.zadatak.mapper.PoljePopunjenoMapper;
import com.zadatak.zadatak.model.FormularPopunjen;
import com.zadatak.zadatak.model.Polje;
import com.zadatak.zadatak.model.PoljePopunjeno;
import com.zadatak.zadatak.repository.PoljePopunjenoRepository;
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
public class PoljePopunjenoService {

    private final PoljePopunjenoRepository poljePopunjenoRepository;
    private final PoljeService poljeService;
    private final PoljePopunjenoMapper poljePopunjenoMapper;
    private final PoljeMapper poljeMapper;
    private final FormularMapper formularMapper;
    private final FormularPopunjenMapper formularPopunjenMapper;
    private final FormularService formularService;

    public List<PoljePopunjenoDTO> getAll() {
        List<PoljePopunjeno> popunjenaPolje = poljePopunjenoRepository.findAll();
        List<PoljePopunjenoDTO> poljePopunjenoDTOs = new ArrayList<>();
        for (PoljePopunjeno p : popunjenaPolje) {
            PoljePopunjenoDTO poljeDTO = poljePopunjenoMapper.toPoljePopunjenoDTO(p);
            poljePopunjenoDTOs.add(poljeDTO);
        }return poljePopunjenoDTOs;
    }

    public Optional<PoljePopunjenoDTO> getById(int id){
        Optional<PoljePopunjeno> poljePopunjeno = poljePopunjenoRepository.findById(id);
        if (poljePopunjeno.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjeno polje za zadati ID");
        }
        return Optional.of(poljePopunjenoMapper.toPoljePopunjenoDTO(poljePopunjeno.get()));
    }

    public Optional<PoljePopunjenoDTO> createPoljePopunjeno(PoljePopunjenoDTO poljePopunjenoDTO, FormularPopunjenDTO formularPopunjenDTO) throws Exception{
        Optional<PoljeDTO> poljeDTO = poljeService.getById(poljePopunjenoDTO.getPoljeId());
        if (poljeDTO.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjeno polje za zadati ID");
        }
        FormularPopunjen formularPopunjen = formularPopunjenMapper.toFormularPopunjen(formularPopunjenDTO);
        Optional<FormularDTO> formularDTOOptional = formularService.getFormularById(formularPopunjenDTO.getFormularId());
        formularPopunjen.setFormular(formularMapper.toFormular(formularDTOOptional.get()));
        Polje polje = poljeMapper.toPolje(poljeDTO.get());
        polje.setFormular(formularMapper.toFormular(formularDTOOptional.get()));
        PoljePopunjeno poljePopunjeno = poljePopunjenoMapper.toPoljePopunjeno(poljePopunjenoDTO);
        poljePopunjeno.setPolje(polje);
        poljePopunjeno.setFormularPopunjen(formularPopunjen);
        PoljePopunjeno poljePopunjenoSaved = poljePopunjenoRepository.save(poljePopunjeno);
        return Optional.of(poljePopunjenoMapper.toPoljePopunjenoDTO(poljePopunjenoSaved));
    }

    public Optional<PoljePopunjenoDTO> updatePoljePopunjeno(PoljePopunjenoDTO poljePopunjenoDTO) throws Exception{
        Optional<PoljePopunjeno> poljePopunjenoOptional = poljePopunjenoRepository.findById(poljePopunjenoDTO.getId());
        if (poljePopunjenoOptional.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjeno polje za zadati ID");
        }
        PoljePopunjeno poljePopunjeno = poljePopunjenoOptional.get();
        poljePopunjenoMapper.updatePoljePopunjenoFromDTO(poljePopunjenoDTO, poljePopunjeno);
        PoljePopunjeno poljePopunjenoUpdated = poljePopunjenoRepository.save(poljePopunjeno);

        return Optional.of(poljePopunjenoMapper.toPoljePopunjenoDTO(poljePopunjenoUpdated));
    }

    public boolean deletePoljePopunjeno(PoljePopunjenoDTO poljePopunjenoDTO) throws Exception {
        Optional<PoljePopunjeno> poljePopunjenoOptional = poljePopunjenoRepository.findById(poljePopunjenoDTO.getId());
        if (poljePopunjenoOptional.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjeno polje za zadati ID");
        }
        poljePopunjenoRepository.deleteById(poljePopunjenoDTO.getId());
        return true;
    }

    public List<PoljePopunjenoDTO> getByFormularPopunjenId(int id) {
        List<PoljePopunjeno> popunjenaPolja = poljePopunjenoRepository.findAllByFormularPopunjen_Id(id);
        List<PoljePopunjenoDTO> poljePopunjenoDTOs = new ArrayList<>();
        for (PoljePopunjeno p : popunjenaPolja) {
            PoljePopunjenoDTO poljeDTO = poljePopunjenoMapper.toPoljePopunjenoDTO(p);
            poljePopunjenoDTOs.add(poljeDTO);
        }
        return poljePopunjenoDTOs;
    }
}

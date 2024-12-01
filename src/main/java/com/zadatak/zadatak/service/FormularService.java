package com.zadatak.zadatak.service;

import com.zadatak.zadatak.dto.FormularDTO;
import com.zadatak.zadatak.dto.PoljeDTO;
import com.zadatak.zadatak.mapper.FormularMapper;
import com.zadatak.zadatak.model.Formular;
import com.zadatak.zadatak.repository.FormularRepository;
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
public class FormularService {

    private final FormularRepository formularRepository;
    private final PoljeService poljeService;
    private final FormularMapper formularMapper;

    public List<FormularDTO> getAll() {
        List<Formular> formulars = formularRepository.findAll();

        List<FormularDTO> formularDTOs = new ArrayList<>();
        for (Formular formular : formulars) {
            List<PoljeDTO> polja = poljeService.getByFormularId(formular.getId());
            FormularDTO formularDTO = formularMapper.toFormularDTO(formular, polja);
            formularDTOs.add(formularDTO);
        }

        return formularDTOs;
    }

    public Optional<FormularDTO> getFormularById(int id){
        Optional<Formular> formular = formularRepository.findById(id);
        if (formular.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjen formular za zadati ID");
        }

        List<PoljeDTO> polja = new ArrayList<>();
        polja = poljeService.getByFormularId(id);
        FormularDTO formularDTO = formularMapper.toFormularDTO(formular.get(), polja);
        return Optional.of(formularDTO);
    }

    public Optional<FormularDTO> createFormular(FormularDTO formularDTO) throws Exception {
        Formular formular = formularMapper.toFormular(formularDTO);
        Formular savedFormular = formularRepository.save(formular);

        List<PoljeDTO> poljaSaved = new ArrayList<>();
        for (PoljeDTO poljeDTO : formularDTO.getPolja()) {
            Optional<PoljeDTO> poljeSaved = poljeService.createPolje(poljeDTO, formularMapper.toFormularDTO(savedFormular));
            poljeSaved.ifPresent(poljaSaved::add);
        }

        return Optional.of(formularMapper.toFormularDTO(savedFormular, poljaSaved));
    }

    public Optional<FormularDTO> updateFormular(FormularDTO formularDTO) throws Exception {
        Optional<Formular> formularOptional = formularRepository.findById(formularDTO.getId());
        if (formularOptional.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjen formular za uneti ID");
        }

        Formular formular = formularOptional.get();
        formularMapper.updateFormularFromDTO(formularDTO, formular);
        Formular updatedFormular = formularRepository.save(formular);

        List<PoljeDTO> poljaUpdated = new ArrayList<>();
        for (PoljeDTO poljeDTO : formularDTO.getPolja()) {
            Optional<PoljeDTO> poljeUpdated = poljeService.updatePolje(poljeDTO);
            poljeUpdated.ifPresent(poljaUpdated::add);
        }

        return Optional.of(formularMapper.toFormularDTO(updatedFormular, poljaUpdated));
    }

    public boolean deleteFormular(FormularDTO formularDTO) throws Exception {
        Optional<Formular> formularOptional = formularRepository.findById(formularDTO.getId());
        if (formularOptional.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjen formular za uneti ID");
        }

        List<PoljeDTO> poljaZaBrisanje = poljeService.getByFormularId(formularDTO.getId());
        for (PoljeDTO poljeDTO : poljaZaBrisanje) {
            poljeService.deletePolje(poljeDTO);
        }

        formularRepository.deleteById(formularDTO.getId());
        return true;
    }
}
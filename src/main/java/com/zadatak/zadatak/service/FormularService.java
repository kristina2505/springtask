package com.zadatak.zadatak.service;

import com.zadatak.zadatak.dto.FormularDTO;
import com.zadatak.zadatak.dto.PoljeDTO;
import com.zadatak.zadatak.mapper.FormularMapper;
import com.zadatak.zadatak.model.Formular;
import com.zadatak.zadatak.model.Polje;
import com.zadatak.zadatak.repository.FormularRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<FormularDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Formular> formulars = formularRepository.findAll(pageable);

        return formulars.map(formular -> {
            List<PoljeDTO> polja = poljeService.getByFormularId(formular.getId());
            return formularMapper.toFormularDTO(formular, polja);
        });
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
        if (formular.getPolja() != null) {
            for (Polje polje : formular.getPolja()) {
                polje.setFormular(formular);
            }
        }
        Formular savedFormular = formularRepository.save(formular);

        return Optional.of(formularMapper.toFormularDTO(savedFormular));
    }

    public Optional<FormularDTO> updateFormular(FormularDTO formularDTO) throws Exception {
        Optional<Formular> formularOptional = formularRepository.findById(formularDTO.getId());
        if (formularOptional.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjen formular za uneti ID");
        }

        Formular formular = formularOptional.get();
        formularMapper.updateFormularFromDTO(formularDTO, formular);
        List<PoljeDTO> poljaUpdated = new ArrayList<>();
        for (PoljeDTO poljeDTO : formularDTO.getPolja()) {
            Optional<PoljeDTO> poljeUpdated = poljeService.updatePolje(poljeDTO);
            poljeUpdated.ifPresent(poljaUpdated::add);
        }
        Formular updatedFormular = formularRepository.save(formular);
        return Optional.of(formularMapper.toFormularDTO(updatedFormular,poljaUpdated));
    }

    public boolean deleteFormular(FormularDTO formularDTO) throws Exception {
        Optional<Formular> formularOptional = formularRepository.findById(formularDTO.getId());
        if (formularOptional.isEmpty()) {
            throw new EntityNotFoundException("Nije pronadjen formular za uneti ID");
        }

        formularRepository.deleteById(formularDTO.getId());
        return true;
    }
}

package com.zadatak.zadatak.service;

import com.zadatak.zadatak.dto.FormularDTO;
import com.zadatak.zadatak.dto.FormularPopunjenDTO;
import com.zadatak.zadatak.dto.PoljePopunjenoDTO;
import com.zadatak.zadatak.mapper.FormularMapper;
import com.zadatak.zadatak.mapper.FormularPopunjenMapper;
import com.zadatak.zadatak.model.Formular;
import com.zadatak.zadatak.model.FormularPopunjen;
import com.zadatak.zadatak.model.PoljePopunjeno;
import com.zadatak.zadatak.repository.FormularPopunjenRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class FormularPopunjenService {
    private final FormularPopunjenRepository formularPopunjenRepository;
    private final FormularService formularService;
    private final PoljePopunjenoService poljePopunjenoService;
    private final FormularPopunjenMapper formularPopunjenMapper;
    private final FormularMapper formularMapper;

    public List<FormularPopunjenDTO> getAll() {
        List<FormularPopunjen> popunjeniFormulari = formularPopunjenRepository.findAll();
        List<FormularPopunjenDTO> popunjneniFormulariDTO = new ArrayList<>();
        for (FormularPopunjen formularPopunjen : popunjeniFormulari) {
            List<PoljePopunjenoDTO> popunjenaPolja = poljePopunjenoService.getByFormularPopunjenId(formularPopunjen.getId());
            FormularPopunjenDTO formularPopunjenDTO = formularPopunjenMapper.toFormularPopunjenDTO(formularPopunjen, popunjenaPolja);
            formularPopunjenDTO.setFormularId(formularPopunjen.getFormular().getId());
            popunjneniFormulariDTO.add(formularPopunjenDTO);
        }
        return popunjneniFormulariDTO;
    }

    public Optional<FormularPopunjenDTO> getById(int id){
        Optional<FormularPopunjen> formularPopunjen = formularPopunjenRepository.findById(id);
        if (formularPopunjen.isEmpty()) {
            throw new EntityNotFoundException("Nije popunjen pronadjen formular za zadati ID");
        }
        Optional<FormularDTO> formularDTO = formularService.getFormularById(formularPopunjen.get().getFormular().getId());
        List<PoljePopunjenoDTO> poljaPopunjenaDTO = new ArrayList<>();
        poljaPopunjenaDTO = poljePopunjenoService.getByFormularPopunjenId(id);
        FormularPopunjenDTO formularPopunjenDTO = formularPopunjenMapper.toFormularPopunjenDTO(formularPopunjen.get(), poljaPopunjenaDTO);
        formularPopunjenDTO.setFormularId(formularDTO.get().getId());
        return Optional.of(formularPopunjenDTO);
    }

    public Optional<FormularPopunjenDTO> save(FormularPopunjenDTO formularPopunjenDTO) throws Exception {
        Optional<FormularDTO> formularDTO = formularService.getFormularById(formularPopunjenDTO.getFormularId());
        if (formularDTO.isEmpty()) {
            throw new Exception("Nije pronadjen formular za zadati ID");
        }

        Formular formular = formularMapper.toFormular(formularDTO.get());
        FormularPopunjen formularPopunjen = formularPopunjenMapper.toFormularPopunjen(formularPopunjenDTO);
        formularPopunjen.setFormular(formular);
        for (PoljePopunjeno poljePopunjeno : formularPopunjen.getPopunjenaPolja()){
            poljePopunjeno.setFormularPopunjen(formularPopunjen);
        }

        FormularPopunjen formularPopunjenSaved = formularPopunjenRepository.save(formularPopunjen);

        return Optional.of(formularPopunjenMapper.toFormularPopunjenDTO(formularPopunjenSaved));
    }

    public Optional<FormularPopunjenDTO> update(FormularPopunjenDTO formularPopunjenDTO) throws Exception {
        Optional<FormularPopunjen> formularPopunjenOptional = formularPopunjenRepository.findById(formularPopunjenDTO.getId());
        if (formularPopunjenOptional.isEmpty()) {
            throw new Exception("Nije pronadjen formular za uneti ID");
        }

        FormularPopunjen formularPopunjen = formularPopunjenOptional.get();
        formularPopunjenMapper.updateFormularPopunjenFromDTO(formularPopunjenDTO, formularPopunjen);

        FormularPopunjen formularPopunjenUpdated = formularPopunjenRepository.save(formularPopunjen);
        List<PoljePopunjenoDTO> poljaPopunjenaUpdated = new ArrayList<>();
        for (PoljePopunjenoDTO poljePopunjenoDTO : formularPopunjenDTO.getPopunjenaPolja()) {
            Optional<PoljePopunjenoDTO> poljePopunjenoSaved = poljePopunjenoService.updatePoljePopunjeno(poljePopunjenoDTO);
            poljePopunjenoSaved.ifPresent(poljaPopunjenaUpdated::add);
        }

        return Optional.of(formularPopunjenMapper.toFormularPopunjenDTO(formularPopunjenUpdated, poljaPopunjenaUpdated));
    }

    public boolean delete(FormularPopunjenDTO formularPopunjenDTO) throws Exception {
        Optional<FormularPopunjen> formularPopunjenOptional = formularPopunjenRepository.findById(formularPopunjenDTO.getId());
        if (formularPopunjenOptional.isEmpty()) {
            throw new Exception("Nije pronadjen formular za uneti ID");
        }
        formularPopunjenRepository.deleteById(formularPopunjenDTO.getId());
        return true;
    }

    public List<FormularPopunjen> countPopunjeniFormulari(LocalDateTime startOfYesterday, LocalDateTime endOfYesterday) {
        List<FormularPopunjen> popunjeniFormulari = new ArrayList<>();
        popunjeniFormulari = formularPopunjenRepository.findByVremeKreiranjaBetween(startOfYesterday, endOfYesterday);
        return popunjeniFormulari;
    }


}

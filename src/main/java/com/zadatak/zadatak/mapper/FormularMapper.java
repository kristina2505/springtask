package com.zadatak.zadatak.mapper;

import com.zadatak.zadatak.dto.FormularDTO;
import com.zadatak.zadatak.dto.PoljeDTO;
import com.zadatak.zadatak.model.Formular;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormularMapper {
    /*public FormularDTO toFormularDTO(Formular formular, List<PoljeDTO> polja) {
        FormularDTO formularDTO = new FormularDTO();
        formularDTO.setNaziv(formular.getNaziv());
        formularDTO.setPolja(polja);
        return formularDTO;
    }

    public Formular toFormular(FormularDTO formularDTO) {
        Formular formular = new Formular();
        formular.setNaziv(formularDTO.getNaziv());
        return formular;
    }*/
    // Metoda za konverziju DTO -> entitet (za kreiranje)
    Formular toFormular(FormularDTO formularDTO);

    // Metoda za ažuriranje postojećeg entiteta iz DTO-a
    @Mapping(target = "vremeKreiranja", ignore = true)
    @Mapping(target = "vremePoslednjeIzmene", ignore = true)
    void updateFormularFromDTO(FormularDTO formularDTO, @MappingTarget Formular formular);

    // Metoda za konverziju entitet -> DTO
    FormularDTO toFormularDTO(Formular formular);

    // Ako koristiš polja, dodaj metode i za njih
    @Mapping(target = "polja", source = "poljaDTO")
    FormularDTO toFormularDTO(Formular formular, List<PoljeDTO> poljaDTO);
}

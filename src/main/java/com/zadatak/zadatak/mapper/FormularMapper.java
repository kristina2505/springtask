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

    Formular toFormular(FormularDTO formularDTO);

    @Mapping(target = "vremeKreiranja", ignore = true)
    @Mapping(target = "vremePoslednjeIzmene", ignore = true)
    void updateFormularFromDTO(FormularDTO formularDTO, @MappingTarget Formular formular);

    FormularDTO toFormularDTO(Formular formular);

    @Mapping(target = "polja", source = "poljaDTO")
    FormularDTO toFormularDTO(Formular formular, List<PoljeDTO> poljaDTO);
}

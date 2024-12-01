package com.zadatak.zadatak.mapper;

import com.zadatak.zadatak.dto.PoljeDTO;
import com.zadatak.zadatak.model.Formular;
import com.zadatak.zadatak.model.Polje;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PoljeMapper {

    @Mapping(target = "formular.id", source = "formularId")
    Polje toPolje(PoljeDTO poljeDTO);

    @Mapping(target = "formularId", source = "formular.id")
    PoljeDTO toPoljeDTO(Polje polje);

    @Mapping(target = "vremeKreiranja", ignore = true)
    @Mapping(target = "vremePoslednjeIzmene", ignore = true)
    void updatePoljeFromDTO(PoljeDTO poljeDTO, @MappingTarget Polje polje);
}

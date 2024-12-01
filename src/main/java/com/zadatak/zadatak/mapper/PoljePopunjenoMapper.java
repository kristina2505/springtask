package com.zadatak.zadatak.mapper;

import com.zadatak.zadatak.dto.PoljePopunjenoDTO;
import com.zadatak.zadatak.model.PoljePopunjeno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PoljePopunjenoMapper {

    @Mapping(source = "polje.id", target = "poljeId")
    @Mapping(source = "formularPopunjen.id", target = "formularPopunjenId")
    PoljePopunjenoDTO toPoljePopunjenoDTO(PoljePopunjeno poljePopunjeno);

    PoljePopunjeno toPoljePopunjeno(PoljePopunjenoDTO poljePopunjenoDTO);

    @Mapping(target = "vremeKreiranja", ignore = true)
    @Mapping(target = "vremePoslednjeIzmene", ignore = true)
    void updatePoljePopunjenoFromDTO(PoljePopunjenoDTO poljePopunjenoDTO, @MappingTarget PoljePopunjeno poljePopunjeno);

}

package com.zadatak.zadatak.mapper;

import com.zadatak.zadatak.dto.FormularPopunjenDTO;
import com.zadatak.zadatak.dto.PoljePopunjenoDTO;
import com.zadatak.zadatak.model.Formular;
import com.zadatak.zadatak.model.FormularPopunjen;
import com.zadatak.zadatak.model.PoljePopunjeno;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormularPopunjenMapper {

    @Mapping(source = "formularPopunjen.formular.id", target = "formularId")
    @Mapping(source = "poljaPopunjena", target = "popunjenaPolja")
    FormularPopunjenDTO toFormularPopunjenDTO(FormularPopunjen formularPopunjen, List<PoljePopunjenoDTO> poljaPopunjena);

    @Mapping(source = "formularPopunjen.formular.id", target = "formularId")
    FormularPopunjenDTO toFormularPopunjenDTO(FormularPopunjen formularPopunjen);


    FormularPopunjen toFormularPopunjen(FormularPopunjenDTO formularPopunjenDTO);

    @Mapping(target = "vremeKreiranja", ignore = true)
    @Mapping(target = "vremePoslednjeIzmene", ignore = true)
    void updateFormularPopunjenFromDTO(FormularPopunjenDTO formularPopunjenDTO, @MappingTarget FormularPopunjen formularPopunjen);
}

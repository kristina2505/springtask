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
    /*public FormularPopunjenDTO toFormularPopunjenDTO(FormularPopunjen formularPopunjen, Formular formular, List<PoljePopunjenoDTO> poljaPopunjena) {
        FormularPopunjenDTO formularPopunjenDTO = new FormularPopunjenDTO();
        formularPopunjenDTO.setVremeKreiranja(formularPopunjen.getVremeKreiranja());
        formularPopunjenDTO.setFormularId(formular.getId());
        formularPopunjenDTO.setPopunjenaPolja(poljaPopunjena);
        return formularPopunjenDTO;
    }

    public FormularPopunjen toFormular(FormularPopunjenDTO formularPopunjenDTO, Formular formular) {
        FormularPopunjen formularPopunjen = new FormularPopunjen();
        formularPopunjen.setFormular(formular);
        return formularPopunjen;
    }*/

    @Mapping(source = "formularPopunjen.formular.id", target = "formularId") // Mapiranje id-a iz Formular entiteta u formularId DTO
    @Mapping(source = "poljaPopunjena", target = "popunjenaPolja") // Mapiranje popunjenih polja
    FormularPopunjenDTO toFormularPopunjenDTO(FormularPopunjen formularPopunjen, List<PoljePopunjenoDTO> poljaPopunjena);

    // Mapiranje iz FormularPopunjenDTO u FormularPopunjen entitet
    //@Mapping(source = "formular", target = "formulario") // Mapiranje formular objekta
    FormularPopunjen toFormularPopunjen(FormularPopunjenDTO formularPopunjenDTO);

    @Mapping(target = "vremeKreiranja", ignore = true) // Ignorišemo vreme kreiranja
    @Mapping(target = "vremePoslednjeIzmene", ignore = true) // Ignorišemo vreme poslednje izmene, biće postavljeno ručno
    void updateFormularPopunjenFromDTO(FormularPopunjenDTO formularPopunjenDTO, @MappingTarget FormularPopunjen formularPopunjen);
}

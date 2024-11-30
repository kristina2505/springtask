package com.zadatak.zadatak.mapper;

import com.zadatak.zadatak.dto.PoljePopunjenoDTO;
import com.zadatak.zadatak.model.PoljePopunjeno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PoljePopunjenoMapper {

    /*public PoljePopunjenoDTO toPoljePopunjenoDTO(PoljePopunjeno polje) {
        PoljePopunjenoDTO p = new PoljePopunjenoDTO();
        p.setFormularPopunjenId(polje.getFormularPopunjen().getId());
        p.setVrednostBroj(polje.getVrednostBroj());
        p.setVrednostTekst(polje.getVrednostTekst());
        p.setPoljeId(polje.getPolje().getId());
        p.setVremeKreiranja(polje.getVremeKreiranja());
        p.setVremePoslednjeIzmene(polje.getVremePoslednjeIzmene());
        return p;
    }

    public PoljePopunjeno toPoljePoljePopunjeno(PoljePopunjenoDTO poljePopunjenoDTO, FormularPopunjen formularPopunjen, Polje polje) {
        PoljePopunjeno p = new PoljePopunjeno();
        //p.setId(poljePopunjenoDTO.getPoljeId());
        p.setFormularPopunjen(formularPopunjen);
        p.setVrednostBroj(poljePopunjenoDTO.getVrednostBroj());
        p.setVrednostTekst(poljePopunjenoDTO.getVrednostTekst());
        p.setPolje(polje);
        return p;
    }*/

    @Mapping(source = "polje.id", target = "poljeId")
    @Mapping(source = "formularPopunjen.id", target = "formularPopunjenId")
    PoljePopunjenoDTO toPoljePopunjenoDTO(PoljePopunjeno poljePopunjeno);

    PoljePopunjeno toPoljePopunjeno(PoljePopunjenoDTO poljePopunjenoDTO);

    @Mapping(target = "vremeKreiranja", ignore = true) // Ignorišemo vreme kreiranja
    @Mapping(target = "vremePoslednjeIzmene", ignore = true) // Ignorišemo vreme poslednje izmene, biće postavljeno ručno
    void updatePoljePopunjenoFromDTO(PoljePopunjenoDTO poljePopunjenoDTO, @MappingTarget PoljePopunjeno poljePopunjeno);

}

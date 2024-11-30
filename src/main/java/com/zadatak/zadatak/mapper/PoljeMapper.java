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

    /*public PoljeDTO toPoljeDTO(Polje polje) {
        PoljeDTO p = new PoljeDTO();
        p.setFormularId(polje.getFormular().getId());
        p.setNaziv(polje.getNaziv());
        p.setPrikazniRedosled(polje.getPrikazniRedosled());
        p.setTipPolja(polje.getTip());
        p.setVremeKreiranja(polje.getVremeKreiranja());
        p.setVremePoslednjeIzmene(polje.getVremePoslednjeIzmene());
        return p;
    }

    public Polje toPolje(PoljeDTO poljeDTO, Formular formular) {
        Polje p = new Polje();
        p.setFormular(formular);
        p.setNaziv(poljeDTO.getNaziv());
        p.setPrikazniRedosled(poljeDTO.getPrikazniRedosled());
        p.setTip(poljeDTO.getTipPolja());
        return p;
    }*/

    @Mapping(target = "formular.id", source = "formularId") // Formular će se setovati ručno u servisu
    Polje toPolje(PoljeDTO poljeDTO);

    // Konverzija iz Polje entiteta u PoljeDTO
    @Mapping(target = "formularId", source = "formular.id")
    PoljeDTO toPoljeDTO(Polje polje);

    @Mapping(target = "vremeKreiranja", ignore = true) // Ignorišemo vreme kreiranja
    @Mapping(target = "vremePoslednjeIzmene", ignore = true) // Ignorišemo vreme poslednje izmene, biće postavljeno ručno
    void updatePoljeFromDTO(PoljeDTO poljeDTO, @MappingTarget Polje polje);
}

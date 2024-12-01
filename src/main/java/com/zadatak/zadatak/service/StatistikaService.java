package com.zadatak.zadatak.service;

import com.zadatak.zadatak.model.FormularPopunjen;
import com.zadatak.zadatak.model.Statistika;
import com.zadatak.zadatak.repository.StatistikaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class StatistikaService {

    private final StatistikaRepository statistikaRepository;
    private final FormularPopunjenService formularPopunjenService;


    public void saveStatistics(int brojPopunjenihFormulara) {
        Statistika statistika = new Statistika();
        statistika.setDatum(LocalDate.now());
        statistika.setBrojPopunjenihFormulara(brojPopunjenihFormulara);
        statistikaRepository.save(statistika);
    }

    public int countFilledFormsYesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        LocalDateTime startOfYesterday = yesterday.atStartOfDay();
        LocalDateTime endOfYesterday = yesterday.atTime(23, 59, 59);

        List<FormularPopunjen> formulariPopunjeniJuce = formularPopunjenService
                .countPopunjeniFormulari(startOfYesterday, endOfYesterday);

        return formulariPopunjeniJuce.size();
    }


}

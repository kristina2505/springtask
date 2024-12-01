package com.zadatak.zadatak.scheduler;

import com.zadatak.zadatak.service.StatistikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private final StatistikaService statistikaService;

    @Autowired
    public ScheduledTask(StatistikaService statistikaService) {
        this.statistikaService = statistikaService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void calculateDailyStatistics() {
        int brojPopunjenihFormulara = statistikaService.countFilledFormsYesterday();

        statistikaService.saveStatistics(brojPopunjenihFormulara);
    }
}

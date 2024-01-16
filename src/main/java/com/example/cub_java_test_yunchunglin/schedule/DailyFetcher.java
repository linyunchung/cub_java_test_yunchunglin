package com.example.cub_java_test_yunchunglin.schedule;

import com.example.cub_java_test_yunchunglin.entity.ExchangeData;
import com.example.cub_java_test_yunchunglin.repo.ExchangeRepository;
import com.example.cub_java_test_yunchunglin.service.RateAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class DailyFetcher {

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    RateAPIService rateAPIService;

    public DailyFetcher(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }
    @Scheduled(cron = "0 0 18 * * ?") // 每天 18:00 執行
    public void fetchAndSaveForexData() {
        // get all data
        List<ExchangeData> dailyFetch = rateAPIService.fetch();

        // get today's data
        String today = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
        for (ExchangeData d : dailyFetch) {
            if(!d.getDate().equals(today)){
                continue;
            }
            System.out.println("Updating...Date: " + d.getDate()+ ", USD/NTD: " + d.getRate());
            exchangeRepository.insert(d);
            System.out.println("Updated today's rates.");
            return;
        }
        System.out.println("No updates.");
    }



}

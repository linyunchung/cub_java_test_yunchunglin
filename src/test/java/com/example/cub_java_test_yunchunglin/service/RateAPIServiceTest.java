package com.example.cub_java_test_yunchunglin.service;

import com.example.cub_java_test_yunchunglin.entity.ExchangeData;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
class RateAPIServiceTest {

    @Autowired
    RateAPIService rateAPIService;

    @Test
    void fetchTest() {
        List<ExchangeData> exData = rateAPIService.fetch();
        System.out.println(exData);

        LocalDate today = LocalDate.now();
        if(today.getDayOfWeek().equals(DayOfWeek.SATURDAY)
            || today.getDayOfWeek().equals(DayOfWeek.SUNDAY))   {
            today = today.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }else if(LocalTime.now().isBefore(LocalTime.of(18,5))){
            today = today.minusDays(1);
        }
        String todayStr = DateTimeFormatter.ofPattern("yyyyMMdd").format(today);
        System.out.println("today = " + todayStr);

        for (ExchangeData d : exData) {
            if(!d.getDate().equals(todayStr)){
                continue;
            }
            System.out.println("Date: " + d.getDate()+ ", USD/NTD: " + d.getRate());
            assertFalse(d.getDate().isEmpty());
            assertFalse(d.getRate().isEmpty());
        }
    }

}
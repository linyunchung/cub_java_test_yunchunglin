package com.example.cub_java_test_yunchunglin.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Document
@NoArgsConstructor
public class ExchangeData {
    @Id
    private String id;
    @Indexed(unique = true)
    @JsonAlias("Date")
    private String date;
    private Currency currency = Currency.USD;
    @JsonAlias("USD/NTD")
    private String rate;
    private String fetchTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

    public ExchangeData(String date, String rate) {
        this.date = date;
        this.rate = rate;
    }

}

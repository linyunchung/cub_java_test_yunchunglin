package com.example.cub_java_test_yunchunglin.service;

import com.example.cub_java_test_yunchunglin.entity.ExchangeData;
import com.example.cub_java_test_yunchunglin.entity.HistoryRequest;
import com.example.cub_java_test_yunchunglin.entity.ResCurrency;
import com.example.cub_java_test_yunchunglin.repo.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForexService {


    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    private final DateTimeFormatter slashes = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final DateTimeFormatter plain  = DateTimeFormatter.ofPattern("yyyyMMdd");

    public List<ResCurrency> getHistory(HistoryRequest request) throws Exception {


        if (!request.getCurrency().equals("usd")) {
            throw new Exception("幣別不符");
        }

        LocalDate startLD = LocalDate.parse(request.getStartDate(), slashes);
        LocalDate endLD = LocalDate.parse(request.getEndDate(), slashes);

        if (startLD.isAfter(endLD)
                || startLD.isBefore(LocalDate.now().minusYears(1))
                || endLD.isAfter(LocalDate.now().minusDays(1))
        ) {
            throw new IllegalArgumentException();
        }

        System.out.println("looking for between " + plain.format(startLD) + " and "+ plain.format(endLD));
        Query query = new Query();
        query.addCriteria(Criteria.where("date")
                .gte(plain.format(startLD))
                .lte(plain.format(endLD)));
        List<ExchangeData> datas = mongoTemplate.find(query, ExchangeData.class);
        List<ResCurrency>currency = new ArrayList<>();
        for(ExchangeData d : datas){
            currency.add(new ResCurrency(d.getDate(), d.getRate()));
        }
        return currency;
    }

}

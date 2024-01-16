package com.example.cub_java_test_yunchunglin.service;

import com.example.cub_java_test_yunchunglin.entity.ExchangeData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RateAPIService {

    private final String url = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";

    public List<ExchangeData> fetch() {
        ResponseEntity<String> response = new RestTemplate().getForEntity(url, String.class);
        System.out.println(response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            return mapper.readValue(response.getBody(), new TypeReference<List<ExchangeData>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get rates from API");
        }
    }
}

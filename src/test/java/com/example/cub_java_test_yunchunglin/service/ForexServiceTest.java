package com.example.cub_java_test_yunchunglin.service;

import com.example.cub_java_test_yunchunglin.entity.HistoryRequest;
import com.example.cub_java_test_yunchunglin.entity.ResCurrency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.List;

@SpringBootTest
class ForexServiceTest {

    @Autowired
    ForexService forexService;

    @Test
    void getHistoryTest() throws Exception {

        HistoryRequest req = new HistoryRequest();
        req.setCurrency("usd");
        req.setStartDate("2023/12/30");
        req.setEndDate("2024/01/10");

        List<ResCurrency> result = forexService.getHistory(req);

        System.out.println(result);
        assertFalse(result.isEmpty());
    }

}
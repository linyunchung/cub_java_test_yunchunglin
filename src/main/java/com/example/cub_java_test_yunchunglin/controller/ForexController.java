package com.example.cub_java_test_yunchunglin.controller;

import com.example.cub_java_test_yunchunglin.entity.HistoryRequest;
import com.example.cub_java_test_yunchunglin.service.ForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forex")
public class ForexController {

    @Autowired
    ForexService forexService;

    @PostMapping("/history")
    public HistoryResponse getForexHistory(@RequestBody HistoryRequest historyRequest) {

        try {
            return HistoryResponse.success(forexService.getHistory(historyRequest));
        } catch (IllegalArgumentException e) {
            return HistoryResponse.badArgs();
        } catch (Exception e) {
            return HistoryResponse.syserr();
        }

    }
}

package com.example.cub_java_test_yunchunglin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoryRequest {

    String startDate;
    String endDate;
    String currency;
}

package com.example.cub_java_test_yunchunglin.controller;

import com.example.cub_java_test_yunchunglin.entity.ResCurrency;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@ToString
public class HistoryResponse {

    @Getter
    private ResError error;

    @Getter
    private List<ResCurrency> currency;

    //failure cases
    public static HistoryResponse badArgs() {
        return new HistoryResponse(ErrorCode.BAD_TIMEFRAMES);
    }
    public static HistoryResponse syserr() {
        return new HistoryResponse(ErrorCode.ERROR);
    }
    private HistoryResponse(ErrorCode error) {
        this.error = new ResError(error.getCode(), error.getMessage());
        this.currency = new ArrayList<>();
    }

    //success cases
    public static HistoryResponse success(List<ResCurrency> currency) {
        return new HistoryResponse(ErrorCode.SUCCESS, currency);
    }

    private HistoryResponse(ErrorCode error, List<ResCurrency> currency) {
        this.error = new ResError(error.getCode(), error.getMessage());
        this.currency = currency;
    }
}

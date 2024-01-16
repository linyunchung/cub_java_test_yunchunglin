package com.example.cub_java_test_yunchunglin.controller;

import lombok.Getter;

public enum ErrorCode {

    BAD_TIMEFRAMES("E001","日期區間不符"),
    SUCCESS("0000","成功"),
    ERROR("500","系統錯誤");

    @Getter
    String code;
    @Getter
    String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

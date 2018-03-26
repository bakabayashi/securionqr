package com.securion.qr.application.core.dto;

import lombok.Getter;

public enum ErrorType {
    GENERAL_UNEXPECTED_ERROR("QR_GEN"),
    GENERAL_VALIDATION_ERROR("QR_VAL"),
    QR_CODE_NOT_FOUND("QR_1");

    @Getter
    private final String code;

    ErrorType(String code) {
        this.code = code;
    }
}

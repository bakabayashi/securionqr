package com.securion.qr.application.core.dto;

import lombok.Getter;

public class ErrorDTO {
    @Getter
    private final String errorCode;

    public ErrorDTO(ErrorType errorType) {
        this.errorCode = errorType.getCode();
    }
}

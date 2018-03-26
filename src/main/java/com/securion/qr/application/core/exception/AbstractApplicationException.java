package com.securion.qr.application.core.exception;

import com.securion.qr.application.core.dto.ErrorType;
import lombok.Getter;

abstract class AbstractApplicationException extends RuntimeException {
    @Getter
    private final ErrorType errorType;

    public AbstractApplicationException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

}
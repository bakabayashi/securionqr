package com.securion.qr.infrastructure.rest;

import com.securion.qr.application.core.dto.ErrorDTO;
import com.securion.qr.application.core.exception.QrCodeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.securion.qr.application.core.dto.ErrorType.GENERAL_UNEXPECTED_ERROR;
import static com.securion.qr.application.core.dto.ErrorType.GENERAL_VALIDATION_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler({ MethodArgumentNotValidException.class, MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class})
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processAbstractBadRequestException(Exception ex) {
        log.info("Generic badRequest exception handler ", ex);
        return new ErrorDTO(GENERAL_VALIDATION_ERROR);
    }

    @ExceptionHandler(QrCodeNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorDTO processQrCodeNotFoundException(QrCodeNotFoundException ex) {
        log.info("QrCodeNotFoundException handler ", ex);
        return new ErrorDTO(ex.getErrorType());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO processUnexpectedInternalException(Exception ex) {
        log.error("Exception handler ", ex);
        return new ErrorDTO(GENERAL_UNEXPECTED_ERROR);
    }

}
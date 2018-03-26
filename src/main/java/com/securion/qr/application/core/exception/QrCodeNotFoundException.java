package com.securion.qr.application.core.exception;

import java.util.UUID;

import static com.securion.qr.application.core.dto.ErrorType.QR_CODE_NOT_FOUND;
import static java.lang.String.format;

public class QrCodeNotFoundException extends AbstractApplicationException{
    private static final String MESSAGE = "QrCode with uuid=%s not found.";

    public QrCodeNotFoundException(UUID uuid) {
        super(format(MESSAGE, uuid.toString()), QR_CODE_NOT_FOUND);
    }
}

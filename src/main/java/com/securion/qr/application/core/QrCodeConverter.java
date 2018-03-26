package com.securion.qr.application.core;

import com.securion.qr.application.core.dto.PersistedQrCodeDTO;
import com.securion.qr.application.core.dto.QrCodeUpdateDTO;
import com.securion.qr.application.core.entity.QrCode;

public class QrCodeConverter {
    public static PersistedQrCodeDTO toDto(QrCode entity) {
        PersistedQrCodeDTO result = new PersistedQrCodeDTO();
        result.setUuid(entity.getUuid());

        return result;
    }

    public static void update(QrCodeUpdateDTO qrCodeUpdateDTO, QrCode entity) {
        entity.setPhoneIdentifier(qrCodeUpdateDTO.getPhoneIdentifier());
    }
}

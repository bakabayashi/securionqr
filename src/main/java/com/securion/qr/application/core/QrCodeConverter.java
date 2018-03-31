package com.securion.qr.application.core;

import com.securion.qr.application.core.dto.PersistedQrCodeDTO;
import com.securion.qr.application.core.dto.QrCodeUpdateDTO;
import com.securion.qr.application.core.entity.QrCode;
import com.securion.qr.application.service.UrlUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QrCodeConverter {
    private final UrlUtils urlUtils;

    public PersistedQrCodeDTO toDto(QrCode entity) {
        PersistedQrCodeDTO result = new PersistedQrCodeDTO();
        result.setUuid(entity.getUuid());
        result.setUrl(urlUtils.generateRedirectUrl(entity.getUuid()));

        return result;
    }

    public static void update(QrCodeUpdateDTO qrCodeUpdateDTO, QrCode entity) {
        entity.setPhoneIdentifier(qrCodeUpdateDTO.getPhoneIdentifier());
    }
}

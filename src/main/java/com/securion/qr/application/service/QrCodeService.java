package com.securion.qr.application.service;

import com.securion.qr.application.repository.QrCodeRepository;
import com.securion.qr.application.core.dto.PersistedQrCodeDTO;
import com.securion.qr.application.core.dto.QrCodeUpdateDTO;
import com.securion.qr.application.core.entity.QrCode;
import com.securion.qr.application.core.exception.QrCodeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.securion.qr.application.core.QrCodeConverter.toDto;
import static com.securion.qr.application.core.QrCodeConverter.update;

@Service
@AllArgsConstructor
public class QrCodeService {
    private final QrCodeRepository qrCodeRepository;

    public PersistedQrCodeDTO create() {
        return toDto(qrCodeRepository.save(new QrCode()));
    }

    public void processCode(UUID uuid, QrCodeUpdateDTO qrCodeUpdateDTO) {
        QrCode qrCode = qrCodeRepository.findOne(uuid)
                .orElseThrow(() -> new QrCodeNotFoundException(uuid));

        update(qrCodeUpdateDTO, qrCode);


    }

}
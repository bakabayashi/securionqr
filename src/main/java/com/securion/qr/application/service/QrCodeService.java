package com.securion.qr.application.service;

import com.securion.qr.application.core.QrCodeConverter;
import com.securion.qr.application.core.dto.PersistedQrCodeDTO;
import com.securion.qr.application.core.dto.QrCodeUpdateDTO;
import com.securion.qr.application.core.entity.QrCode;
import com.securion.qr.application.core.exception.QrCodeNotFoundException;
import com.securion.qr.application.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.securion.qr.application.core.QrCodeConverter.toRedirectDto;
import static com.securion.qr.application.core.QrCodeConverter.update;
import static com.securion.qr.application.service.SimpUtils.createHeaders;
import static com.securion.qr.configuration.WebSocketSpecificConstants.REPLY_PATH;

@Service
@RequiredArgsConstructor
public class QrCodeService {
    private final QrCodeRepository qrCodeRepository;
    private final QrCodeConverter qrCodeConverter;
    private final SimpMessagingTemplate template;
    private final UrlUtils urlUtils;

    public PersistedQrCodeDTO create() {
        return qrCodeConverter.toDto(qrCodeRepository.save(new QrCode()));
    }

    public void processCode(UUID uuid, QrCodeUpdateDTO qrCodeUpdateDTO) {
       QrCode qrCode = qrCodeRepository.findOne(uuid)
                .orElseThrow(() -> new QrCodeNotFoundException(uuid));

        update(qrCodeUpdateDTO, qrCode);

        template.convertAndSendToUser(qrCode.getSessionId(), REPLY_PATH, toRedirectDto(qrCode, urlUtils.createSuccessfulUrl()), createHeaders(qrCode.getSessionId()));
    }
}

package com.securion.qr.application.service;

import com.securion.qr.application.core.QrCodeConverter;
import com.securion.qr.application.core.dto.PersistedQrCodeDTO;
import com.securion.qr.application.core.dto.QrCodeUpdateDTO;
import com.securion.qr.application.core.entity.QrCode;
import com.securion.qr.application.core.exception.QrCodeNotFoundException;
import com.securion.qr.application.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.securion.qr.application.core.QrCodeConverter.update;

@Service
@RequiredArgsConstructor
public class QrCodeService {
    private final QrCodeRepository qrCodeRepository;
    private final QrCodeConverter qrCodeConverter;
    private final SimpMessagingTemplate template;

    public PersistedQrCodeDTO create() {
        return qrCodeConverter.toDto(qrCodeRepository.save(new QrCode()));
    }

    public void processCode(UUID uuid, QrCodeUpdateDTO qrCodeUpdateDTO) {
       /* QrCode qrCode = qrCodeRepository.findOne(uuid)
                .orElseThrow(() -> new QrCodeNotFoundException(uuid));*/

        /*update(qrCodeUpdateDTO, qrCode);*/

        template.convertAndSendToUser(uuid.toString(),"/queue","dupa", createHeaders(uuid.toString()));
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}

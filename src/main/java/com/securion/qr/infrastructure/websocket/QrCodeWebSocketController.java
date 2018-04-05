package com.securion.qr.infrastructure.websocket;

import com.securion.qr.application.core.entity.QrCode;
import com.securion.qr.application.core.exception.QrCodeNotFoundException;
import com.securion.qr.application.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class QrCodeWebSocketController {
    private final QrCodeRepository qrCodeRepository;

    private static final String SIMP_SESSION_KEY = "simpSessionAttributes";
    private static final String SESSION_ID_KEY = "sessionId";

    @MessageMapping("/register")
    @SuppressWarnings("unchecked")
    public void receive(@Payload Message message, Principal user) throws Exception {
        UUID qrCodeUuid = UUID.fromString(new String((byte[]) message.getPayload()));
        QrCode qrCode = qrCodeRepository.findOne(qrCodeUuid).orElseThrow(() -> new QrCodeNotFoundException(qrCodeUuid));
        Map<String, String> attributesMap = (Map<String, String>) message.getHeaders().get(SIMP_SESSION_KEY);
        qrCode.setSessionId(attributesMap.get(SESSION_ID_KEY));
        qrCodeRepository.save(qrCode);
    }
}

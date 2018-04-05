package com.securion.qr.infrastructure.websocket;

import com.securion.qr.application.core.entity.QrCode;
import com.securion.qr.application.core.exception.QrCodeNotFoundException;
import com.securion.qr.application.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class QrCodeWebSocketController {
    private final QrCodeRepository qrCodeRepository;

    @MessageMapping("/register")
    public void receive(Message message, SimpMessageHeaderAccessor accessor) throws Exception {
        UUID qrCodeUuid = UUID.fromString(new String((byte[]) message.getPayload()));
        QrCode qrCode = qrCodeRepository.findOne(qrCodeUuid).orElseThrow(() -> new QrCodeNotFoundException(qrCodeUuid));

        qrCode.setSessionId(accessor.getSessionId());

        qrCodeRepository.save(qrCode);
    }
}

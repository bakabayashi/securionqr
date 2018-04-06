package com.securion.qr.infrastructure.websocket;

import com.securion.qr.application.core.entity.QrCode;
import com.securion.qr.application.core.exception.QrCodeNotFoundException;
import com.securion.qr.application.repository.QrCodeRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;

import java.util.Optional;
import java.util.UUID;

import static com.securion.qr.application.service.SimpUtils.createHeaders;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class QrCodeWebSocketControllerTest {
    @InjectMocks
    private QrCodeWebSocketController qrCodeWebSocketController;

    @Mock
    private QrCodeRepository qrCodeRepository;

    @Rule
    public ExpectedException expectedExceptionRule = ExpectedException.none();

    private static final UUID QR_CODE_UUID = UUID.randomUUID();
    private static final String SESSION_ID = "1";

    @Test
    public void shouldRegisterWebSocketClient() throws Exception {
        //given
        Message message = createMessage();
        QrCode qrCode = createQrCode();
        given(qrCodeRepository.findOne(QR_CODE_UUID)).willReturn(Optional.of(qrCode));
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        accessor.setSessionId(SESSION_ID);

        //when
        qrCodeWebSocketController.receive(message, accessor);

        //then
        assertThat(qrCode.getSessionId()).isEqualTo(SESSION_ID);
    }

    @Test
    public void shouldNotRegisterWebSocketClientAndThrowException() throws Exception {
        //given
        Message message = createMessage();
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        accessor.setSessionId(SESSION_ID);
        expectedExceptionRule.expect(QrCodeNotFoundException.class);

        //when
        qrCodeWebSocketController.receive(message, accessor);

        //then
    }

    private Message createMessage() {
        return new Message() {
            @Override
            public Object getPayload() {
                return QR_CODE_UUID.toString().getBytes();
            }

            @Override
            public MessageHeaders getHeaders() {
                return createHeaders(SESSION_ID);
            }
        };
    }

    private QrCode createQrCode() {
        QrCode qrCode = new QrCode();
        qrCode.setUuid(QR_CODE_UUID);

        return qrCode;
    }
}
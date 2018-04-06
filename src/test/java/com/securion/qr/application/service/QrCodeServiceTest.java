package com.securion.qr.application.service;

import com.securion.qr.application.core.QrCodeConverter;
import com.securion.qr.application.core.dto.QrCodeRedirectDTO;
import com.securion.qr.application.core.dto.QrCodeUpdateDTO;
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
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Optional;
import java.util.UUID;

import static com.securion.qr.configuration.WebSocketSpecificConstants.REPLY_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class QrCodeServiceTest {

    private static final UUID QR_CODE_UUID = UUID.randomUUID();
    private static final String PHONE_IDENTIFIER = "123456";
    private static final String SESSION_ID = "1";
    @InjectMocks
    private QrCodeService qrCodeService;

    @Mock
    private QrCodeRepository qrCodeRepository;

    @Mock
    private QrCodeConverter qrCodeConverter;

    @Mock
    private SimpMessagingTemplate template;

    @Mock
    private UrlUtils urlUtils;

    @Rule
    public ExpectedException expectedExceptionRule = ExpectedException.none();

    @Test
    public void shouldNotProcessAndThrowException() {
        //given
        QrCodeUpdateDTO qrCodeUpdateDTO = new QrCodeUpdateDTO();
        expectedExceptionRule.expect(QrCodeNotFoundException.class);

        //when
        qrCodeService.processCode(UUID.randomUUID(), qrCodeUpdateDTO);

        //then
    }

    @Test
    public void shouldProcessAndSendMessage() {
        //given
        QrCodeUpdateDTO qrCodeUpdateDTO = createQrCodeUpdateDTO();
        QrCode qrCode = createQrCode();
        given(qrCodeRepository.findOne(QR_CODE_UUID)).willReturn(Optional.of(qrCode));

        //when
        qrCodeService.processCode(QR_CODE_UUID, qrCodeUpdateDTO);

        //then
        assertThat(qrCode.getPhoneIdentifier()).isNotNull();
        assertThat(qrCode.getPhoneIdentifier()).isEqualTo(PHONE_IDENTIFIER);
        verify(template).convertAndSendToUser(eq(SESSION_ID), eq(REPLY_PATH), anyObject(), anyMap());
    }

    private QrCodeUpdateDTO createQrCodeUpdateDTO() {
        QrCodeUpdateDTO qrCodeUpdateDTO = new QrCodeUpdateDTO();
        qrCodeUpdateDTO.setPhoneIdentifier(PHONE_IDENTIFIER);
        return qrCodeUpdateDTO;
    }

    private QrCode createQrCode() {
        QrCode qrCode = new QrCode();
        qrCode.setUuid(QR_CODE_UUID);
        qrCode.setSessionId(SESSION_ID);

        return qrCode;
    }

}
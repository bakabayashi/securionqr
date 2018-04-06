package com.securion.qr.application.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SimpUtilsTest {

    @Test
    public void shouldCreateMessageHeaders() {
        //given
        String sessionId = "0";

        //when
        MessageHeaders headers = SimpUtils.createHeaders(sessionId);

        //then
        assertThat(headers).isNotNull();
        assertThat(headers.get("simpSessionId")).isNotNull();
        assertThat(headers.get("simpSessionId")).isEqualTo(sessionId);
        assertThat(headers.get("simpMessageType")).isNotNull();
        assertThat(headers.get("simpMessageType")).isEqualTo(SimpMessageType.MESSAGE);
    }

}
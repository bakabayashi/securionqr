package com.securion.qr.application.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UrlUtilsTest {
    @InjectMocks
    private UrlUtils urlUtils;

    private UUID QR_CODE_UUID = UUID.randomUUID();

    @Before
    public void setUp() {
        urlUtils.setContextPath("/path");
        urlUtils.setDomain("domain");
        urlUtils.setProtocol("protocol");
    }

    @Test
    public void shouldCreateMobileTargetUrl() {
        //given

        //when
        String mobileTargetUrl = urlUtils.createMobileTargetUrl(QR_CODE_UUID);

        //then
        assertThat(mobileTargetUrl).isNotNull();
        assertThat(mobileTargetUrl).isEqualTo("protocol://domain/path/api/v1/qr-codes/" + QR_CODE_UUID.toString());
    }
}
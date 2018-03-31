package com.securion.qr.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.securion.qr.infrastructure.rest.QrCodeRestEndpoint.QR_CODES_PATH;

@Component
public class UrlUtils {
    @Value("${server.servlet.contextPath}")
    private String contextPath;

    @Value("${server.domain}")
    private String domain;

    @Value("${server.protocol}")
    private String protocol;

    private static final String PROTOCOL_DELIMITER = "://";
    private static final String DELIMITER = "/";

    public String generateRedirectUrl(UUID uuid) {
        return protocol + PROTOCOL_DELIMITER + domain + contextPath + QR_CODES_PATH + DELIMITER + uuid.toString();
    }
}

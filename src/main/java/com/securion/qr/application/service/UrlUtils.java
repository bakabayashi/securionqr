package com.securion.qr.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.securion.qr.infrastructure.rest.QrCodeRestEndpoint.QR_CODES_PATH;

@Component
public class UrlUtils {
    private String contextPath;

    private String domain;

    private String protocol;

    private static final String PROTOCOL_DELIMITER = "://";
    private static final String DELIMITER = "/";

    public String createMobileTargetUrl(UUID uuid) {
        return protocol + PROTOCOL_DELIMITER + domain + contextPath + QR_CODES_PATH + DELIMITER + uuid.toString();
    }

    String createSuccessfulUrl() {
        return "successful.html";
    }

    @Value("${server.protocol}")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Value("${server.domain}")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Value("${server.servlet.contextPath}")
    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
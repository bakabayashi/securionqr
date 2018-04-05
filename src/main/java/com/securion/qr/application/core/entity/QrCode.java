package com.securion.qr.application.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class QrCode {
    private UUID uuid = UUID.randomUUID();
    private String phoneIdentifier;
    private String sessionId;
}

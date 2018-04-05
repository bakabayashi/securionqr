package com.securion.qr.application.core.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class QrCodeRedirectDTO {
    private UUID uuid;
    private String redirectUrl;
}

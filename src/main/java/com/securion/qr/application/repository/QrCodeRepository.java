package com.securion.qr.application.repository;

import com.securion.qr.application.core.entity.QrCode;

import java.util.Optional;
import java.util.UUID;

public interface QrCodeRepository {
    QrCode save(QrCode entity);

    Optional<QrCode> findOne(UUID uuid);
}

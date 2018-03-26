package com.securion.qr.infrastructure.repository;

import com.securion.qr.application.repository.QrCodeRepository;
import com.securion.qr.application.core.entity.QrCode;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

@Component
public class CustomQrCodeRepository implements QrCodeRepository {
    private Map<UUID, QrCode> memory = newHashMap();

    @Override
    public QrCode save(QrCode entity) {
        memory.put(entity.getUuid(), entity);
        return entity;
    }

    @Override
    public Optional<QrCode> findOne(UUID uuid) {
        QrCode result = memory.get(uuid);
        return result == null ? Optional.empty() : Optional.of(result);
    }
}

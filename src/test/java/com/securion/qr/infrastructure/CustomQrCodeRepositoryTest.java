package com.securion.qr.infrastructure;

import com.securion.qr.application.core.entity.QrCode;
import com.securion.qr.infrastructure.repository.CustomQrCodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CustomQrCodeRepositoryTest {

    @InjectMocks
    private CustomQrCodeRepository qrCodeRepository;

    @Test
    public void shouldSaveNewQrCode() {
        //given
        QrCode qrCode = new QrCode();

        //when
        QrCode savedEntity = qrCodeRepository.save(qrCode);

        //then
        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity.getUuid()).isNotNull();
    }

    @Test
    public void shouldFindEntity() {
        //given
        QrCode qrCode = new QrCode();
        qrCodeRepository.save(qrCode);

        //when
        Optional<QrCode> found = qrCodeRepository.findOne(qrCode.getUuid());

        //then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get()).isNotNull();
        assertThat(found.get().getUuid()).isEqualTo(qrCode.getUuid());
    }

}
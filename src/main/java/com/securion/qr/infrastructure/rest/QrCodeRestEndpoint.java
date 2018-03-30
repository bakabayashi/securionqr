package com.securion.qr.infrastructure.rest;

import com.securion.qr.application.service.QrCodeService;
import com.securion.qr.application.core.dto.QrCodeUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/qr-codes")
@RequiredArgsConstructor
public class QrCodeRestEndpoint {
    private final QrCodeService qrCodeService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<?> createQRCode() {
        return new ResponseEntity(qrCodeService.create(), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(ACCEPTED)
    public ResponseEntity<?> processQRCodeScan(@PathVariable("uuid") UUID uuid,
                                               @Valid @RequestBody QrCodeUpdateDTO qrCodeUpdateDTO) {
        qrCodeService.processCode(uuid, qrCodeUpdateDTO);
        return new ResponseEntity(ACCEPTED);
    }
}

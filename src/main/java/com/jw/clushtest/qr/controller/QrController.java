package com.jw.clushtest.qr.controller;

import com.jw.clushtest.qr.service.QrCodeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/share")
@RequiredArgsConstructor
public class QrController {
    private final QrCodeService qrCodeService;

    @GetMapping("/qr")
    public ResponseEntity<byte[]> getCalendarQR() throws Exception {
        return qrCodeService.getCalendarQR();
    }
}

package com.jw.clushtest.qr.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jw.clushtest.calendar.dto.CalendarEventDTO;
import com.jw.clushtest.calendar.service.CalendarEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QrCodeService {

    private final CalendarEventService eventService;
    private final ObjectMapper objectMapper;

    public ResponseEntity<byte[]> getCalendarQR() throws Exception {
        List<CalendarEventDTO> events = eventService.getEventsForCurrentMonth();

        List<CalendarEventDTO> eventsRemoveId = events.stream()
                .map(event -> {
                    event.setId(null); // id 필드 값을 null로 설정
                    return event;
                })
                .collect(Collectors.toList());

        String jsonData = objectMapper.writeValueAsString(eventsRemoveId);

        byte[] qrImage = generateQRCode(jsonData, 300, 300);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(qrImage, headers, HttpStatus.OK);
    }

    public byte[] generateQRCode(String text, int width, int height) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // QR 변환시 한글 출력 인코딩 타입

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }
}

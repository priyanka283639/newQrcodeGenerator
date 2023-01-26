package com.weathercast.WeatherReport.CONTROLLER;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @GetMapping("/download/{text}")
    public ResponseEntity<InputStreamResource> downloadQRCode(@PathVariable String text) throws WriterException, IOException {
        ByteArrayOutputStream stream = qrCodeGenerator.generateQRCode(text);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(stream.toByteArray()));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=qrcode.png")
                .body(resource);
    }
}
package com.example.qrmanagementservice.service;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

public interface QrCodeManager {
    byte[] generateQr(String data);

    Mono<String> decodeQr(FilePart image);
}

package com.example.qrmanagementservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface QrCodeManager {
    byte[] generateQr(String data);

    String decodeQr(MultipartFile image);
}

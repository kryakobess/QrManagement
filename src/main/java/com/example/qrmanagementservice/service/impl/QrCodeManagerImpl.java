package com.example.qrmanagementservice.service.impl;

import com.example.qrmanagementservice.model.exception.QrManagerException;
import com.example.qrmanagementservice.service.QrCodeManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class QrCodeManagerImpl implements QrCodeManager {

    private static final int QR_HEIGHT = 200;
    private static final int QR_WIDTH = 200;
    private static final String IMAGE_FORMAT = "png";

    @Override
    public byte[] generateQr(String data) {
        try (var baos = new ByteArrayOutputStream()) {
            log.info("Generating qr for string {}", data);
            var writer = new QRCodeWriter();
            var bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT);
            MatrixToImageWriter.writeToStream(bitMatrix, IMAGE_FORMAT, baos);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("Error during generating qr code for string: {}", data, e);
            throw new QrManagerException("Qr generation exception", e);
        }
    }

    @Override
    public String decodeQr(MultipartFile image) {
        try (var inputStream = new ByteArrayInputStream(image.getBytes())) {
            var decodedResult = new MultiFormatReader()
                    .decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(inputStream)))));
            return decodedResult != null ? decodedResult.getText() : null;
        } catch (Exception e) {
            throw new QrManagerException("Exception while trying to decode qr from image. " +
                    "Please check whether image is a qr or not", e);
        }
    }
}

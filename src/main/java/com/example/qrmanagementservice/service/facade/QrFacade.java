package com.example.qrmanagementservice.service.facade;

import com.example.qrmanagementservice.model.dto.VaccineQrCheckDto;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

public interface QrFacade {
    Mono<byte[]> getQrCodeImageForCitizen(Long citizenId);

    Mono<VaccineQrCheckDto> checkVaccineQrByImage(MultipartFile file);
}

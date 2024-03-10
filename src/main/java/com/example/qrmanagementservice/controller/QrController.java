package com.example.qrmanagementservice.controller;

import com.example.qrmanagementservice.model.dto.VaccineQrCheckDto;
import com.example.qrmanagementservice.service.facade.QrFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qr")
public class QrController {

    private final QrFacade facade;

    @GetMapping(value = "/vaccination/{citizenId}", produces = MediaType.IMAGE_PNG_VALUE)
    public Mono<ResponseEntity<byte[]>> getVaccinationQrCodeImage(@PathVariable Long citizenId) {
        return facade.getQrCodeImageForCitizen(citizenId)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/vaccination/check-image")
    public Mono<ResponseEntity<VaccineQrCheckDto>> checkVaccineQrByImage(@RequestParam("qrCode") MultipartFile multipartFile) {
        return facade.checkVaccineQrByImage(multipartFile)
                .map(ResponseEntity::ok);
    }
}

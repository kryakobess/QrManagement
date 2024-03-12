package com.example.qrmanagementservice.controller;

import com.example.qrmanagementservice.model.dto.VaccineQrCheckDto;
import com.example.qrmanagementservice.service.facade.QrFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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

    @PostMapping(value = "/vaccination/check-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<VaccineQrCheckDto>> checkVaccineQrByImage(@RequestPart("qrCode") Mono<FilePart> file) {
        return facade.checkVaccineQrByImage(file)
                .map(ResponseEntity::ok);
    }
}

package com.example.qrmanagementservice.controller;

import com.example.qrmanagementservice.model.dto.VaccinationCitizenQrDto;
import com.example.qrmanagementservice.model.dto.VaccineQrMessageDto;
import com.example.qrmanagementservice.service.VaccinationQrService;
import com.example.qrmanagementservice.service.mapper.VaccinationCitizenQrMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test/qr")
@RequiredArgsConstructor
public class TestController {

    private final VaccinationQrService vaccinationQrService;
    private final VaccinationCitizenQrMapper vaccinationCitizenQrMapper;

    @PostMapping("/vaccination/create")
    public Mono<ResponseEntity<VaccinationCitizenQrDto>> createQrCode(@RequestBody VaccineQrMessageDto messageDto) {
        return vaccinationQrService.createQrFromMessage(messageDto)
                .map(vaccinationCitizenQrMapper::toDto)
                .map(ResponseEntity::ok);
    }

}



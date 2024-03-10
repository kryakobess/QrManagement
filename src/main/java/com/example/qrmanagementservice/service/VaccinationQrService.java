package com.example.qrmanagementservice.service;

import com.example.qrmanagementservice.model.document.VaccinationCitizenQr;
import com.example.qrmanagementservice.model.dto.VaccineQrMessageDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VaccinationQrService {
    Mono<VaccinationCitizenQr> createQrFromMessage(VaccineQrMessageDto message);
    Mono<VaccinationCitizenQr> getLatestQrCodeForCitizen(Long citizenId);
    Mono<Boolean> verifyQrCode(String qrCodeHash);
}

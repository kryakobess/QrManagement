package com.example.qrmanagementservice.service.impl;

import com.example.qrmanagementservice.model.document.VaccinationCitizenQr;
import com.example.qrmanagementservice.model.dto.VaccineQrMessageDto;
import com.example.qrmanagementservice.repository.VaccinationCitizenQrRepository;
import com.example.qrmanagementservice.service.VaccinationQrService;
import com.example.qrmanagementservice.service.util.Md5HashGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class VaccinationQrServiceImpl implements VaccinationQrService {

    private final VaccinationCitizenQrRepository vaccinationCitizenQrRepository;

    @Override
    public Mono<VaccinationCitizenQr> createQrFromMessage(VaccineQrMessageDto message) {
        VaccinationCitizenQr vaccinationCitizenQr =  VaccinationCitizenQr.builder()
                .citizenId(message.getCitizenId())
                .expireDate(message.getVaccineDurableUntil())
                .qrHash(Md5HashGenerator.generateHashFor(message.toString()))
                .build();
        return vaccinationCitizenQrRepository.insert(vaccinationCitizenQr);
    }

    @Override
    public Mono<VaccinationCitizenQr> getLatestQrCodeForCitizen(Long citizenId) {
        return vaccinationCitizenQrRepository.getTopByCitizenIdOrderByExpireDateDesc(citizenId);
    }

    @Override
    public Mono<Boolean> verifyQrCode(String qrCodeHash) {
        return qrCodeHash == null ?
                Mono.just(false)
                : vaccinationCitizenQrRepository.existsByQrHash(qrCodeHash);
    }

}

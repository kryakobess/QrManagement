package com.example.qrmanagementservice.service.facade;

import com.example.qrmanagementservice.model.document.VaccinationCitizenQr;
import com.example.qrmanagementservice.model.dto.VaccineQrCheckDto;
import com.example.qrmanagementservice.service.QrCodeManager;
import com.example.qrmanagementservice.service.VaccinationQrService;
import com.example.qrmanagementservice.service.util.FileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

@Service
@Slf4j
@RequiredArgsConstructor
public class QrFacadeImpl implements QrFacade {

    private final VaccinationQrService vaccinationQrService;
    private final QrCodeManager qrCodeManager;
    
    @Override
    public Mono<byte[]> getQrCodeImageForCitizen(Long citizenId) {
        log.info("Getting getQrCodeImageForCitizen request for citizenId: {}", citizenId);
        return vaccinationQrService.getLatestQrCodeForCitizen(citizenId)
                .map(VaccinationCitizenQr::getQrHash)
                .map(qrCodeManager::generateQr);
    }

    @Override
    public Mono<VaccineQrCheckDto> checkVaccineQrByImage(MultipartFile file) {
        log.info("Getting checkVaccineQrByImage for file: {}", file.getOriginalFilename());
        FileValidator.validateMultipart(file);
        return Mono.fromCallable(() -> qrCodeManager.decodeQr(file))
                .flatMap(vaccinationQrService::verifyQrCode)
                .map(VaccineQrCheckDto::new);
    }

}

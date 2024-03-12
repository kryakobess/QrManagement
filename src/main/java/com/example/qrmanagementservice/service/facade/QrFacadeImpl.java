package com.example.qrmanagementservice.service.facade;

import com.example.qrmanagementservice.model.document.VaccinationCitizenQr;
import com.example.qrmanagementservice.model.dto.VaccineQrCheckDto;
import com.example.qrmanagementservice.service.QrCodeManager;
import com.example.qrmanagementservice.service.VaccinationQrService;
import com.example.qrmanagementservice.service.util.FileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
    public Mono<VaccineQrCheckDto> checkVaccineQrByImage(Mono<FilePart> file) {
        return file.doOnNext(filePart ->
                        log.info("Getting checkVaccineQrByImage for file: {}", filePart.filename())
                ).doOnNext(FileValidator::validateMultipart)
                .flatMap(qrCodeManager::decodeQr)
                .flatMap(vaccinationQrService::verifyQrCode)
                .doOnNext(verified -> log.info("Qr code is {} verified", verified ? "" : "not"))
                .map(VaccineQrCheckDto::new);
    }

}

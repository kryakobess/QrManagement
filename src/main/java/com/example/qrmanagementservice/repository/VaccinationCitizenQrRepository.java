package com.example.qrmanagementservice.repository;

import com.example.qrmanagementservice.model.document.VaccinationCitizenQr;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VaccinationCitizenQrRepository extends ReactiveMongoRepository<VaccinationCitizenQr, String> {
    Mono<VaccinationCitizenQr> getTopByCitizenIdOrderByExpireDateDesc(Long citizenId);
    Mono<Boolean> existsByQrHash(String qrHash);
}

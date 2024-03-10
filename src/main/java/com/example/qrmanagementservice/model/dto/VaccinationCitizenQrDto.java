package com.example.qrmanagementservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationCitizenQrDto {
    private String id;
    private String qrHash;
    private Long citizenId;
    private LocalDate expireDate;
}

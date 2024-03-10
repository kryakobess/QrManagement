package com.example.qrmanagementservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccineQrMessageDto {
    Long citizenId;
    LocalDate vaccineDurableUntil;
}

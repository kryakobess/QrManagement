package com.example.qrmanagementservice.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "vaccinationCitizenQr")
public class VaccinationCitizenQr {
    @Id
    private String id;
    private String qrHash;
    private Long citizenId;
    private LocalDate expireDate;
}

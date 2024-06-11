package com.example.qrmanagementservice.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
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

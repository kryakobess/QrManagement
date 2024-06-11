package com.example.qrmanagementservice.service.mapper;

import com.example.qrmanagementservice.model.document.VaccinationCitizenQr;
import com.example.qrmanagementservice.model.dto.VaccinationCitizenQrDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VaccinationCitizenQrMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "qrHash", source = "qrHash")
    @Mapping(target = "citizenId", source = "citizenId")
    @Mapping(target = "expireDate", source = "expireDate")
    VaccinationCitizenQrDto toDto(VaccinationCitizenQr model);
}

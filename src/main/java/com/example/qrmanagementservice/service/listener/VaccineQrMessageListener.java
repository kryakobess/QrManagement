package com.example.qrmanagementservice.service.listener;

import com.example.qrmanagementservice.model.dto.VaccineQrMessageDto;

public interface VaccineQrMessageListener {

    void consume(VaccineQrMessageDto message);
}

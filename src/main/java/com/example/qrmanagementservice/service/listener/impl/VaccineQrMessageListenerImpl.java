package com.example.qrmanagementservice.service.listener.impl;

import com.example.qrmanagementservice.model.dto.VaccineQrMessageDto;
import com.example.qrmanagementservice.service.VaccinationQrService;
import com.example.qrmanagementservice.service.listener.VaccineQrMessageListener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VaccineQrMessageListenerImpl implements VaccineQrMessageListener {

    private final ReactiveKafkaConsumerTemplate<String, VaccineQrMessageDto> kafkaTemplate;
    private final VaccinationQrService vaccinationQrService;

    @PostConstruct
    private void subscribeTopic() {
        kafkaTemplate.receiveAutoAck()
                .doOnNext(it -> log.info(
                        "Received message: topic:{} partition:{} key:{} value:{}",
                        it.topic(), it.partition(), it.key(), it.value()
                ))
                .map(ConsumerRecord::value)
                .doOnError(throwable -> log.error("Error while consuming message.", throwable))
                .subscribe(this::consume);
    }

    @Override
    public void consume(VaccineQrMessageDto message) {
        vaccinationQrService.createQrFromMessage(message)
                .doOnNext(it -> log.info("Registered qr code {}", it))
                .subscribe();
    }

}

package com.example.qrmanagementservice.service.listener.impl;

import com.example.qrmanagementservice.model.dto.VaccineQrMessageDto;
import com.example.qrmanagementservice.service.VaccinationQrService;
import com.example.qrmanagementservice.service.listener.VaccineQrMessageListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VaccineQrMessageListenerImpl implements VaccineQrMessageListener {

    private final ReactiveKafkaConsumerTemplate<String, String> kafkaTemplate;
    private final VaccinationQrService vaccinationQrService;
    private final ObjectMapper objectMapper;

    @EventListener(ApplicationStartedEvent.class)
    private void subscribeTopic() {
        kafkaTemplate.receiveAutoAck()
                .doOnNext(it -> log.info(
                        "Received message: topic:{} partition:{} key:{} value:{}",
                        it.topic(), it.partition(), it.key(), it.value()
                ))
                .map(ConsumerRecord::value)
                .map(this::deserialize)
                .doOnError(throwable -> log.error("Error while consuming message.", throwable))
                .subscribe(this::consume);
    }

    @Override
    public void consume(VaccineQrMessageDto message) {
        vaccinationQrService.createQrFromMessage(message)
                .doOnNext(it -> log.info("Registered qr code {}", it))
                .subscribe();
    }

    private VaccineQrMessageDto deserialize(String message) {
        try {
            return objectMapper.readValue(message, VaccineQrMessageDto.class);
        } catch (JsonProcessingException e) {
            log.error("Error while deserializing message {}", message, e);
            throw new RuntimeException(e);
        }
    }
}

package com.example.qrmanagementservice.configuration;

import com.example.qrmanagementservice.model.dto.VaccineQrMessageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ReceiverOptions<String, VaccineQrMessageDto> vaccineKafkaReceiverOptions(
            @Value("${qrm.kafka.vaccineQrTopicName}") String vaccineQrTopicName,
            KafkaProperties kafkaProperties
    ) {
        return ReceiverOptions
                .<String, VaccineQrMessageDto> create(kafkaProperties.buildConsumerProperties(null))
                .subscription(List.of(vaccineQrTopicName));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, VaccineQrMessageDto> vaccineKafkaConsumerTemplate(
            ReceiverOptions<String, VaccineQrMessageDto> vaccineReceiverOptions
    ) {
        return new ReactiveKafkaConsumerTemplate<>(vaccineReceiverOptions);
    }
}

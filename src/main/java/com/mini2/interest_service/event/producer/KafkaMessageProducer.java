package com.mini2.interest_service.event.producer;

import com.mini2.interest_service.domain.dto.InterestRequestDto;
import com.mini2.interest_service.event.producer.dto.InterestEventDto;
import com.mini2.interest_service.event.producer.dto.InterestPayloadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageProducer {
    private final KafkaTemplate<String, InterestEventDto> kafkaTemplate;


    public void sendInterestEvent(InterestRequestDto dto) {
        InterestPayloadDto payload = new InterestPayloadDto(dto.getName());

        InterestEventDto event = new InterestEventDto(
                "관심사 정보",
                LocalDateTime.now(),
                "interest-service",
                payload
        );

        kafkaTemplate.send("UserInterestInfo", event);
    }
}
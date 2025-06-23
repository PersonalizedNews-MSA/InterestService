package com.mini2.interest_service.event.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestEventDto {
    private String eventId;
    private LocalDateTime timestamp;
    private String sourceService;
    private InterestPayloadDto payload;
}

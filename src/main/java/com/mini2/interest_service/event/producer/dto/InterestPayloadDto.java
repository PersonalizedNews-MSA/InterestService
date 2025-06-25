package com.mini2.interest_service.event.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestPayloadDto {
    private String userId;
    private List<String> name;
}

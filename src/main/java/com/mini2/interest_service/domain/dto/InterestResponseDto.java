package com.mini2.interest_service.domain.dto;

import com.mini2.interest_service.domain.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterestResponseDto {
    private Long id;
    private String name;

    public static InterestResponseDto from(Interest interest) {
        return InterestResponseDto.builder()
                .id(interest.getId())
                .name(interest.getName())
                .build();
    }
}
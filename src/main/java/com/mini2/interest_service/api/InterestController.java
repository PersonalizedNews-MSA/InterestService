package com.mini2.interest_service.api;

import java.util.List;

import com.mini2.interest_service.common.web.context.GatewayRequestHeaderUtils;
import com.mini2.interest_service.domain.dto.InterestRequestDto;
import com.mini2.interest_service.domain.dto.InterestResponseDto;
import com.mini2.interest_service.event.producer.KafkaMessageProducer;
import com.mini2.interest_service.service.InterestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/interests/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class InterestController {
    private final InterestService interestService;
    private final KafkaMessageProducer kafkaMessageProducer;

    @PostMapping("/")
    public ResponseEntity<Void> addInterest(@RequestBody InterestRequestDto dto) {
        Long userId = Long.valueOf(GatewayRequestHeaderUtils.getUserIdOrThrowException());
        interestService.addInterest(dto, userId);
        kafkaMessageProducer.sendInterestEvent(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<InterestResponseDto>> getInterests() {
        Long userId = Long.valueOf(GatewayRequestHeaderUtils.getUserIdOrThrowException());
        List<InterestResponseDto> list = interestService.getInterests(userId);

        return ResponseEntity.ok(list);
    }


    @PutMapping("/")
    public ResponseEntity<Void> updateInterests(@RequestBody InterestRequestDto dto) {
        Long userId = Long.valueOf(GatewayRequestHeaderUtils.getUserIdOrThrowException());
        interestService.updateInterest(dto, userId);
        kafkaMessageProducer.sendInterestEvent(dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteInterests() {
        Long userId = Long.valueOf(GatewayRequestHeaderUtils.getUserIdOrThrowException());
        interestService.deleteByUserId(userId);

        return ResponseEntity.ok().build();
    }

}
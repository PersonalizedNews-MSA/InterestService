package com.mini2.interest_service.api;

import java.util.List;

import com.mini2.interest_service.domain.dto.InterestRequestDto;
import com.mini2.interest_service.domain.dto.InterestResponseDto;
import com.mini2.interest_service.service.InterestService;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/")
    public ResponseEntity<Void> addInterest(@RequestBody InterestRequestDto dto, HttpServletRequest request) {
        interestService.addInterest(dto, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<InterestResponseDto>> getInterests(HttpServletRequest request) {
        List<InterestResponseDto> list = interestService.getInterests(request);
        return ResponseEntity.ok(list);
    }


    @PutMapping("/")
    public ResponseEntity<Void> updateInterests(
            HttpServletRequest request,
            @RequestBody InterestRequestDto dto) {

        interestService.updateInterest(dto, request);
        return ResponseEntity.noContent().build();
    }
}
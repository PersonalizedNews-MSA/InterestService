package com.mini2.interest_service.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mini2.interest_service.common.exception.BadParameter;
import com.mini2.interest_service.domain.entity.Interest;
import com.mini2.interest_service.domain.entity.UserInterest;
import com.mini2.interest_service.domain.dto.InterestRequestDto;
import com.mini2.interest_service.domain.dto.InterestResponseDto;
import com.mini2.interest_service.domain.repository.InterestRepository;
import com.mini2.interest_service.domain.repository.UserInterestRepository;
import com.mini2.interest_service.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;
    private final UserInterestRepository userInterestRepository;

    @Transactional
    public void addInterest(InterestRequestDto dto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserIdFromToken(token);

        for (String name : dto.getName()) {
            Interest interest = interestRepository.findByName(name)
                    .orElseGet(() -> interestRepository.save(
                            Interest.builder().name(name).build()
                    ));
            if (userInterestRepository.existsByUserIdAndInterest(userId, interest)) {
                throw new BadParameter("중복된 관심사입니다.");
            }

            UserInterest mapping = UserInterest.builder()
                    .userId(userId)
                    .interest(interest)
                    .build();

            userInterestRepository.save(mapping);
        }
    }

    @Transactional
    public void updateInterest(InterestRequestDto dto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserIdFromToken(token);

        userInterestRepository.deleteByUserId(userId);

        for (String name : dto.getName()) {
            Interest interest = interestRepository.findByName(name)
                    .orElseGet(() -> interestRepository.save(
                            Interest.builder().name(name).build()
                    ));

            UserInterest mapping = UserInterest.builder()
                    .userId(userId)
                    .interest(interest)
                    .build();

            userInterestRepository.save(mapping);
        }
    }


    @Transactional(readOnly = true)
    public List<InterestResponseDto> getInterests(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = JwtUtil.getUserIdFromToken(token);

        return userInterestRepository.findByUserId(userId).stream()
                .map(UserInterest::getInterest)
                .map(InterestResponseDto::from)
                .collect(Collectors.toList());
    }

}

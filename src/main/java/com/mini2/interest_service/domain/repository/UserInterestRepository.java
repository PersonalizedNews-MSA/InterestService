package com.mini2.interest_service.domain.repository;

import java.util.List;

import com.mini2.interest_service.domain.entity.Interest;
import com.mini2.interest_service.domain.entity.UserInterest;
import com.mini2.interest_service.domain.entity.UserInterestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestRepository extends JpaRepository<UserInterest, UserInterestId>{
    List<UserInterest> findByUserId(Long userId);

    boolean existsByUserIdAndInterest(Long userId, Interest interest);
    void deleteByUserIdAndInterest(Long userId, Interest interest);

    void deleteByUserId(Long userId);
}
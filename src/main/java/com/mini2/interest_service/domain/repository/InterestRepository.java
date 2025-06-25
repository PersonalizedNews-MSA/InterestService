package com.mini2.interest_service.domain.repository;

import java.util.Optional;

import com.mini2.interest_service.domain.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InterestRepository extends JpaRepository<Interest, Long>{
    Optional<Interest> findByName(String name);
    
}
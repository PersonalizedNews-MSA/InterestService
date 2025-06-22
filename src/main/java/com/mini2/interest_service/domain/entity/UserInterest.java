package com.mini2.interest_service.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@IdClass(UserInterestId.class)
@Table(name = "user_interest")
public class UserInterest {
    @Id
    @ManyToOne
    @JoinColumn(name = "interest_id")
    private Interest interest;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name= "created_time")
    private LocalDateTime createdTime;

    @PrePersist
    public void prePersist() {
        this.createdTime = LocalDateTime.now();
    }
    
}

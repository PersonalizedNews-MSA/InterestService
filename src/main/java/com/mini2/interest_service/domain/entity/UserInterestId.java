package com.mini2.interest_service.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserInterestId implements Serializable{
    private Long userId;
    private Long interest;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInterestId)) return false;
        UserInterestId that = (UserInterestId) o;
        return Objects.equals(userId, that.userId)
            && Objects.equals(interest, that.interest);
    }
    
    @Override
    public int hashCode(){

        return Objects.hash(userId,interest);
    }

}
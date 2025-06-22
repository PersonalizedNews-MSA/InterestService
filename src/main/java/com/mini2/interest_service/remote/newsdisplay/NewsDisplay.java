package com.mini2.interest_service.remote.newsdisplay;

import com.mini2.interest_service.common.dto.ApiResponseDto;
import com.mini2.interest_service.domain.dto.InterestRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NewsDisplayService", url = "http://news-display-service:8080", path = "api/news/v1")
public interface NewsDisplay {

    @PostMapping("/")
    ApiResponseDto<Void> sendUserInterests(@RequestBody InterestRequestDto dto);
}

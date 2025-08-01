package com.example.tournament_service.feign;

import com.example.tournament_service.dto.UserMatchScoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "match-service", configuration = FeignClientConfig.class)
public interface MatchServiceFeignClient {

    @GetMapping("/match/user/{userId}/scores")
    ResponseEntity<List<UserMatchScoreDto>> getUserScores(@PathVariable("userId") Long userId);
}


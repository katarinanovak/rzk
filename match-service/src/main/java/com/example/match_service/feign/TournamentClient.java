package com.example.match_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tournament-service", configuration = FeignClientConfig.class)
public interface TournamentClient {

    @GetMapping("/tournament/{id}/name")
    String getTournamentName(@PathVariable("id") Long tournamentId);
}

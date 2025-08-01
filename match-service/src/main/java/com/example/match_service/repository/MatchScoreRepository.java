package com.example.match_service.repository;

import com.example.match_service.model.MatchScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchScoreRepository extends JpaRepository<MatchScore, Integer> {
    List<MatchScore> findByMatchIdAndPlayerId(Long matchId, Long playerId);
}

package com.example.tournament_service.repository;

import com.example.tournament_service.model.Participant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByUserId(Long userId);


}

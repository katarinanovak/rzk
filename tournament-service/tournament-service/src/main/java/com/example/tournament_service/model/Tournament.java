package com.example.tournament_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tournaments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private String status; // npr. PLANNED, ONGOING, COMPLETED

    private Long organizerId; // userId iz user-service-a

    @ManyToOne
    @JoinColumn(name = "tournament_type_id")  // promenjeno sa category_id na tournament_type_id
    private TournamentType tournamentType;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Participant> participants;
}

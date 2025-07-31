package com.example.tournament_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;

public class TournamentDto {

    @NotBlank(message = "Naziv turnira je obavezan")
    @Size(min = 3, max = 50, message = "Naziv mora biti između 3 i 50 karaktera")
    private String name;

    @Size(max = 255, message = "Opis može imati najviše 255 karaktera")
    private String description;

    @NotNull(message = "Datum početka je obavezan")
    @Future(message = "Datum početka mora biti u budućnosti")
    private LocalDate startDate;

    @NotNull(message = "Datum završetka je obavezan")
    @Future(message = "Datum završetka mora biti u budućnosti")
    private LocalDate endDate;

    @NotBlank(message = "Status je obavezan")
    private String status;

    @NotNull(message = "ID organizatora je obavezan")

    @NotNull(message = "Tip turnira je obavezan")
    private Long tournamentTypeId;



    private Long organizerId;

    public TournamentDto() {
    }

    public TournamentDto(String name, String description, LocalDate startDate, LocalDate endDate, String status, Long organizerId) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.organizerId = organizerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }
    public Long getTournamentTypeId() {
        return tournamentTypeId;
    }

    public void setTournamentTypeId(Long tournamentTypeId) {
        this.tournamentTypeId = tournamentTypeId;
    }
}

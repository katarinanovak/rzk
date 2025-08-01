package com.example.tournament_service.dto;

import com.example.tournament_service.exception.InvalidDateException;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class RescheduleDto {

    @NotNull(message = "Start date cannot be null")
    private LocalDate newStartDate;

    @NotNull(message = "End date cannot be null")
    private LocalDate newEndDate;


    public RescheduleDto(LocalDate newStartDate, LocalDate newEndDate) {
        this.newStartDate = newStartDate;
        this.newEndDate = newEndDate;
    }

    public LocalDate getNewStartDate() {
        return newStartDate;
    }

    public void setNewStartDate(LocalDate newStartDate) {
        this.newStartDate = newStartDate;
    }


    public LocalDate getNewEndDate() {
        return newEndDate;
    }

    public void setNewEndDate(LocalDate newEndDate) {
        this.newEndDate = newEndDate;
    }

    public void validateDates() {
        LocalDate today = LocalDate.now();
        if (newStartDate.isBefore(today) || newEndDate.isBefore(today)) {
            throw new InvalidDateException("Tournament dates cannot be in the past.");
        }
        if (newStartDate.isAfter(newEndDate)) {
            throw new InvalidDateException("Start date cannot be after end date.");
        }
    }
}
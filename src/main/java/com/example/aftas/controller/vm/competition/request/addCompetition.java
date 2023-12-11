package com.example.aftas.controller.vm.competition.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class addCompetition {

    @NotNull(message = "the date can't be null")
    private LocalDate date;

    @NotNull(message = "the date can't be null")
    private Time startTime;

    @NotNull(message = "the date can't be null")
    private Time endTime;

    private Integer numberOfParticipants;

    @NotNull(message = "the location can't be null")
    @NotBlank(message = "the location can't be blank")
    private String location;

    @NotNull(message = "the amount can't be null")
    @Positive(message = "the amount need to be positive")
    private Double amount;
}

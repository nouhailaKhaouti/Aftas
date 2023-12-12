package com.example.aftas.controller.vm.competition.request;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class addCompetition {

    @NotNull(message = "the date can't be null")
    @Future(message = "the date of the competition can't be in the past")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;

    @NotNull(message = "the date can't be null")
    @DateTimeFormat(pattern = "hh-mm")
    private Time startTime;

    @NotNull(message = "the date can't be null")
    @DateTimeFormat(pattern = "hh-mm")
    private Time endTime;

    @Positive(message = "the number of participants can't be negative")
    @NotNull(message = "the number of Participants can't be null")
    private Integer numberOfParticipants;

    @NotNull(message = "the location can't be null")
    @NotBlank(message = "the location can't be blank")
    private String location;

    @NotNull(message = "the amount can't be null")
    @Positive(message = "the amount need to be positive")
    private Double amount;
}

package com.example.aftas.controller.vm.competition.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class updateCompetition {

    @NotNull(message = "the amount can't be null")
    @Positive(message = "the amount need to be positive")
    private Double amount;
    @Positive(message = "the number of participants can't be negative")
    @NotNull(message = "the number of Participants can't be null")
    private Integer numberOfParticipants;
}

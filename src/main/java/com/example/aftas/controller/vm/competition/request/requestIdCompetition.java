package com.example.aftas.controller.vm.competition.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class requestIdCompetition {

    @NotNull(message = "the id can't be null")
    private Long id;
}

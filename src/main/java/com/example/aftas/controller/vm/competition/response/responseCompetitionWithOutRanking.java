package com.example.aftas.controller.vm.competition.response;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class responseCompetitionWithOutRanking {
    private LocalDate date;

    private Time startTime;

    private Time endTime;

    private Integer numberOfParticipants;

    private String location;

    private Double amount;
}

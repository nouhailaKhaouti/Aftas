package com.example.aftas.controller.vm.competition.response;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class responseCompetitionWithOutRanking {
    private String code;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer numberOfParticipants;

    private String location;

    private Double amount;
}

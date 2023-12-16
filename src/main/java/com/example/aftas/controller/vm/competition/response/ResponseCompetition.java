package com.example.aftas.controller.vm.competition.response;
import com.example.aftas.controller.vm.Ranking.response.RankingResponse;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ResponseCompetition {
    private String code;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer numberOfParticipants;

    private String location;

    private Double amount;

    private List<RankingResponse> rankings;

}

package com.example.aftas.controller.vm.competition.response;
import com.example.aftas.controller.vm.Ranking.response.RankingResponse;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Data
public class ResponseCompetition {

    private LocalDate date;

    private Time startTime;

    private Time endTime;

    private Integer numberOfParticipants;

    private String location;

    private Double amount;

    private List<RankingResponse> rankingList;

}

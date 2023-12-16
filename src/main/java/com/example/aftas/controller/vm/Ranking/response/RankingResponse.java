package com.example.aftas.controller.vm.Ranking.response;

import com.example.aftas.controller.vm.competition.response.responseCompetitionWithOutRanking;
import com.example.aftas.controller.vm.member.response.responseMember;
import lombok.Data;

@Data
public class RankingResponse {
    private responseCompetitionWithOutRanking competition;
    private responseMember member;
    private Integer score;
    private Integer rank;
}

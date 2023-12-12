package com.example.aftas.controller.vm.Ranking.request;

import com.example.aftas.controller.vm.competition.request.requestIdCompetition;
import com.example.aftas.controller.vm.member.request.requestIdMember;
import lombok.Data;

@Data
public class rankingRequest {

    private requestIdMember idMember;

    private requestIdCompetition idCompetition;
}

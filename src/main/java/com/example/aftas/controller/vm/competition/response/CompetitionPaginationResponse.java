package com.example.aftas.controller.vm.competition.response;

import com.example.aftas.controller.vm.member.response.responseMember;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompetitionPaginationResponse {

    private List<responseCompetitionWithOutRanking> competitions;
    private Long totalCompetitions;
    private int currentPage;
    private int totalPages;
}

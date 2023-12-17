package com.example.aftas.controller.vm.hunting.response;

import com.example.aftas.controller.vm.Fish.responseFish;
import com.example.aftas.controller.vm.competition.response.responseCompetitionWithOutRanking;
import com.example.aftas.controller.vm.member.response.responseMember;
import lombok.Data;

@Data
public class responseHunting {
    private responseFish fish;
    private responseCompetitionWithOutRanking competition;
    private responseMember member;
    private Integer numberOfFish;
}

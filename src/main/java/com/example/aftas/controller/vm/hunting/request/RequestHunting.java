package com.example.aftas.controller.vm.hunting.request;

import com.example.aftas.controller.vm.Fish.requestIdFish;
import com.example.aftas.controller.vm.competition.request.requestIdCompetition;
import com.example.aftas.controller.vm.member.request.requestIdMember;
import lombok.Data;

@Data
public class RequestHunting {

    private requestIdCompetition competition;
    private requestIdMember member;
    private requestIdFish fish;

}

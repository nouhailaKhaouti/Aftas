package com.example.aftas.controller.vm.hunting.request;

import com.example.aftas.controller.vm.Fish.requestIdFish;
import com.example.aftas.controller.vm.competition.request.requestIdCompetition;
import com.example.aftas.controller.vm.member.request.requestIdMember;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;

public record RequestHunting(
        String code,Integer num,String name
) {
    public Hunting ToHunting(){
        return Hunting.builder().competition(
                Competition.builder().code(code).build()
        ).member(Member.builder().num(num).build()).fish(Fish.builder().name(name).build()).build();
    }
}

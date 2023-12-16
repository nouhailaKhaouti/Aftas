package com.example.aftas.controller.vm.Ranking.request;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;

public record rankingRequest(Integer num, String code) {

   public Ranking ToRankingEntity(){
       return Ranking.builder().member(
               Member.builder().num(this.num).build()
       ).competition(
               Competition.builder().code(this.code).build()
       ).build();
   }
}

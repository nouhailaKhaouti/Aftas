package com.example.aftas.controller;


import com.example.aftas.controller.vm.Ranking.request.rankingRequest;
import com.example.aftas.controller.vm.Ranking.response.RankingResponse;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.service.facade.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Aftas/api/ranking")
public class RankingController {

    final private RankingService rankingService;

    final ModelMapper modelMapper;


    @GetMapping("/")
    public ResponseEntity<?> getAllRankings() {
            List<Ranking> rankings = rankingService.sortMemberWithPoints();
            List<RankingResponse> rankingResponse=rankings.stream().map(r->modelMapper.map(r,RankingResponse.class)).toList();
            return new ResponseEntity<>(rankingResponse, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addRanking(@Valid @RequestBody() rankingRequest rankingRequest) {
            Ranking ranking=modelMapper.map(rankingRequest,Ranking.class);
            Ranking addedRanking = rankingService.create(ranking);
            RankingResponse rankingResponse=modelMapper.map(addedRanking,RankingResponse.class);
            return new ResponseEntity<>(rankingResponse, HttpStatus.OK);
    }


    @DeleteMapping("/{competition}/{member}")
    public ResponseEntity<?> deleteRanking(@PathVariable("competition") long competition,@PathVariable("member") long member) {

            Ranking ranking=new Ranking();
            Member member1=ranking.getMember();
            member1.setId(member);
            Competition competition1=ranking.getCompetition();
            competition1.setId(competition);
            ranking.setCompetition(competition1);
            ranking.setMember(member1);
            rankingService.delete(ranking);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}

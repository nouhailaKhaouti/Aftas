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
import org.springframework.security.access.prepost.PreAuthorize;
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
            List<Ranking> rankings = rankingService.findAll();
            List<RankingResponse> rankingResponse=rankings.stream().map(r->modelMapper.map(r,RankingResponse.class)).toList();
            return new ResponseEntity<>(rankingResponse, HttpStatus.OK);
    }

    @GetMapping("/{competition}")
    public ResponseEntity<?> getRankingsByCompetition(@PathVariable String competition) {
        List<Ranking> rankings = rankingService.sortMemberWithPoints(competition);
        List<RankingResponse> rankingResponse=rankings.stream().map(r->modelMapper.map(r,RankingResponse.class)).toList();
        return new ResponseEntity<>(rankingResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER') AND hasRole('JUDGES')")
    @PostMapping("/")
    public ResponseEntity<?> addRanking(@Valid @RequestBody() rankingRequest rankingrequest) {
            Ranking addedRanking = rankingService.create(rankingrequest.ToRankingEntity());
            return new ResponseEntity<>(modelMapper.map(addedRanking,RankingResponse.class), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER') AND hasRole('JUDGES')")
    @DeleteMapping("/{competition}/{member}")
    public ResponseEntity<?> deleteRanking(@PathVariable("competition") String competition,@PathVariable("member") Integer member) {

            Ranking ranking=Ranking.builder().competition(
                    Competition.builder().code(competition).build()
            ).member(
                    Member.builder().num(member).build()
            ).build();
            rankingService.delete(ranking);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}

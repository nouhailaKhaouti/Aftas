package com.example.aftas.controller;


import com.example.aftas.entities.Ranking;
import com.example.aftas.service.facade.RankingService;
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
            List<Ranking> rankings = rankingService.findAll();

            return new ResponseEntity<>(rankings, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addRanking(@RequestBody() Ranking ranking) {
            Ranking addedRanking = rankingService.create(ranking);
            return new ResponseEntity<>(addedRanking, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRanking(@PathVariable("id") long id, @RequestBody() Ranking ranking) {
            Ranking addedRanking = rankingService.update(ranking);
            return new ResponseEntity<>(addedRanking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRanking(@PathVariable("id") long id) {

            Ranking ranking=new Ranking();
            ranking.setId(id);
            rankingService.delete(ranking);

            return new ResponseEntity<>(HttpStatus.OK);
    }

}

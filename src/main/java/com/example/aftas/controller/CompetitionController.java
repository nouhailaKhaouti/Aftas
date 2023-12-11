package com.example.aftas.controller;


import com.example.aftas.entities.Competition;
import com.example.aftas.service.facade.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Aftas/api/competition")
public class CompetitionController {

    final private CompetitionService competitionService;

/*
    final ModelMapper modelMapper;
*/

    @GetMapping("/")
    public ResponseEntity<?> getAllCompetitions() {
            List<Competition> competitions = competitionService.findAll();

            return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addCompetition(@RequestBody() Competition competition) {
            Competition addedCompetition = competitionService.create(competition);
            return new ResponseEntity<>(addedCompetition, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompetition(@PathVariable("id") long id, @RequestBody() Competition competition) {
            Competition addedCompetition = competitionService.update(competition);
            return new ResponseEntity<>(addedCompetition, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompetition(@PathVariable("id") long id) {

            Competition competition=new Competition();
            competition.setId(id);
            competitionService.delete(competition);

            return new ResponseEntity<>(HttpStatus.OK);
    }

}

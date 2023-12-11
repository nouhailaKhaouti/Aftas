package com.example.aftas.controller;


import com.example.aftas.controller.vm.competition.request.addCompetition;
import com.example.aftas.controller.vm.competition.response.ResponseCompetition;
import com.example.aftas.entities.Competition;
import com.example.aftas.service.facade.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Aftas/api/competition")
public class CompetitionController {

    final private CompetitionService competitionService;


    final ModelMapper modelMapper;


    @GetMapping("/")
    public ResponseEntity<?> getAllCompetitions() {
            List<Competition> competitions = competitionService.findAll();
            return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addCompetition(@Valid @RequestBody() addCompetition addcompetition) {
            Competition competition=modelMapper.map(addcompetition,Competition.class);
            Competition addedCompetition = competitionService.create(competition);
            ResponseCompetition competition1=modelMapper.map(addedCompetition,ResponseCompetition.class);
            return new ResponseEntity<>(competition1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompetition(@PathVariable("id") long id, @RequestBody() addCompetition addcompetition) {
        Competition competition=modelMapper.map(addcompetition,Competition.class);
        competition.setId(id);
        Competition addedCompetition = competitionService.create(competition);
        ResponseCompetition competition1=modelMapper.map(addedCompetition,ResponseCompetition.class);
        return new ResponseEntity<>(competition1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompetition(@PathVariable("id") long id) {

            Competition competition=new Competition();
            competition.setId(id);
            competitionService.delete(competition);

            return new ResponseEntity<>(HttpStatus.OK);
    }

}

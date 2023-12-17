package com.example.aftas.controller;


import com.example.aftas.controller.vm.competition.request.addCompetition;
import com.example.aftas.controller.vm.competition.response.CompetitionPaginationResponse;
import com.example.aftas.controller.vm.competition.response.ResponseCompetition;
import com.example.aftas.controller.vm.competition.response.responseCompetitionWithOutRanking;
import com.example.aftas.entities.Competition;
import com.example.aftas.service.facade.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/Competitions")
    public ResponseEntity<?> getAllCompetitions(@RequestParam(required = false) String code,@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "4") Integer size) {
            Pageable paging= PageRequest.of(page,size);
            Page<Competition> competitions = competitionService.findCompetitionByCode(Competition.builder().code(code).build(),paging);
            return new ResponseEntity<>(CompetitionPaginationResponse.builder()
                    .totalCompetitions(competitions.getTotalElements())
                    .totalPages(competitions.getTotalPages())
                    .currentPage(competitions.getNumber())
                    .competitions(competitions
                            .getContent()
                            .stream()
                            .map(c->modelMapper
                                    .map(c, responseCompetitionWithOutRanking.class))
                            .toList()).
                    build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCompetitionById(@PathVariable("id") Long id) {
        Competition competitions = competitionService.findById(Competition.builder().id(id).build());
        return new ResponseEntity<>(modelMapper.map(competitions,ResponseCompetition.class), HttpStatus.OK);
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

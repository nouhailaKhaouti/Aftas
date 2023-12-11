package com.example.aftas.service.impl;

import com.example.aftas.Repository.CompetitionRepository;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Competition;
import com.example.aftas.exception.AlreadyExistException;
import com.example.aftas.exception.DateValidationException;
import com.example.aftas.exception.NotFoundException;
import com.example.aftas.service.facade.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    final private CompetitionRepository competitionRepository;

    @Override
    public Competition create(Competition competition){
        if((competition.getStartTime()!=competition.getEndTime())&&(competition.getEndTime().isAfter(competition.getStartTime()))) {
            if (competition.getDate().isAfter(LocalDate.now().plusDays(2))) {
                if (competitionRepository.findCompetitionByDate(competition.getDate()) == null) {
                    return competitionRepository.save(competition);
                }
                throw new AlreadyExistException();
            }
            throw new DateValidationException();
        }
        throw new DateValidationException();
    }

    @Override
    public Competition update(Competition competition) {
        Optional<Competition> competition1=competitionRepository.findById(competition.getId());
        if(competition1.isPresent()) {
            if (competition.getDate().isAfter(LocalDate.now().plusDays(2))) {
                if(competition1.get().getDate()==competition.getDate()){
                    return competitionRepository.save(competition);
                }
                if (competitionRepository.findCompetitionByDate(competition.getDate()) == null) {
                    return competitionRepository.save(competition);
                }
                throw new AlreadyExistException();
            }
            throw new DateValidationException();
        }
        throw new NotFoundException();
    }

    @Override
    public void delete(Competition competition) {
        competitionRepository.delete(competition);
    }

    @Override
    public Competition findById(Competition competition) {
        Optional<Competition> competition1=competitionRepository.findById(competition.getId());
        return competition1.orElse(null);    }

    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }
}

package com.example.aftas.service.impl;

import com.example.aftas.Repository.CompetitionRepository;
import com.example.aftas.entities.Competition;
import com.example.aftas.exception.AlreadyExistException;
import com.example.aftas.exception.DateValidationException;
import com.example.aftas.exception.NotFoundException;
import com.example.aftas.service.facade.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                    String locationAbbreviation = competition.getLocation().substring(0, Math.min(competition.getLocation().length(), 3)).toUpperCase();
                    String formattedDate = competition.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
                    String generatedName = locationAbbreviation + "-" + formattedDate;
                    competition.setCode(generatedName);
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
        if(competition1.isEmpty()){
            throw new NotFoundException();
        }
        return competition1.get();
    }


    @Override
    public Competition findCompetitionByCode(Competition competition){
        return competitionRepository.findCompetitionByCode(competition.getCode());
    }

    @Override
    public Page<Competition> findCompetitionByCode(Competition competition, Pageable paging){
        if(competition.getCode()!=null) {
            return competitionRepository.findCompetitionByCode(competition.getCode(),paging);
        }
        return competitionRepository.findAll(paging);
    }

    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }
}

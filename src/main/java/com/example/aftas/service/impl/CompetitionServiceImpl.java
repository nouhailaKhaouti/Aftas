package com.example.aftas.service.impl;

import com.example.aftas.Repository.CompetitionRepository;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Competition;
import com.example.aftas.service.facade.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    final private CompetitionRepository competitionRepository;

    @Override
    public Competition create(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public Competition update(Competition competition) {
        return competitionRepository.save(competition);
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

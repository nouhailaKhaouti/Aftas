package com.example.aftas.service.impl;

import com.example.aftas.Repository.RankingRepository;
import com.example.aftas.entities.Ranking;
import com.example.aftas.service.facade.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    final private RankingRepository rankingRepository;
    @Override
    public Ranking create(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    @Override
    public Ranking update(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    @Override
    public void delete(Ranking ranking) {
        rankingRepository.delete(ranking);
    }
    @Override
    public Optional<Ranking> findById(Ranking ranking) {
        return rankingRepository.findById(ranking.getId());
    }

    @Override
    public List<Ranking> findAll() {
        return rankingRepository.findAll();
    }
}

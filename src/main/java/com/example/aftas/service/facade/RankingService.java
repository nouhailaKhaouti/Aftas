package com.example.aftas.service.facade;

import com.example.aftas.entities.Ranking;

import java.util.List;
import java.util.Optional;

public interface RankingService {

    Ranking create(Ranking member);

    Ranking update(Ranking member);

    void delete(Ranking member);

    Optional<Ranking> findById(Ranking member);

    List<Ranking> findAll();

}

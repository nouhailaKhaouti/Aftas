package com.example.aftas.service.facade;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;

import java.util.List;

public interface RankingService {

    Ranking create(Ranking member);

    Ranking update(Ranking member);

    void delete(Ranking member);

    Ranking findById(Ranking member);

    List<Ranking> findAll();
    Ranking findByMemberAndCompetition(Member member, Competition competition);

     List<Ranking> sortMemberWithPoints(String code);


    }

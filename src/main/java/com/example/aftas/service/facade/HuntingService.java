package com.example.aftas.service.facade;

import com.example.aftas.entities.Hunting;

import java.util.List;
import java.util.Optional;

public interface HuntingService {

    Hunting create(Hunting member,Double weight);

    Hunting update(Hunting member);

    void delete(Hunting member);

    Optional<Hunting> findById(Hunting member);

    List<Hunting> findAll();

     List<Hunting> findByMemberAndCompetition(Hunting hunting);

    }

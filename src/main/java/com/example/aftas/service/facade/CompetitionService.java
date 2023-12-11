package com.example.aftas.service.facade;

import com.example.aftas.entities.Competition;

import java.util.List;

public interface CompetitionService {

    Competition create(Competition member);

    Competition update(Competition member);

    void delete(Competition member);

    Competition findById(Competition member);

    List<Competition> findAll();

}

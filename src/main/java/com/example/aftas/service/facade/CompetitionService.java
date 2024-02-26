package com.example.aftas.service.facade;

import com.example.aftas.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.zip.DataFormatException;

public interface CompetitionService {

    Competition create(Competition member);

    Competition update(Competition member);

    void delete(Competition member);

    Competition findById(Competition member);

    Page<Competition> findCompetitionByCode(Competition competition, Pageable paging);

    List<Competition> findAll();

    Competition findCompetitionByCode(Competition competition);

    public List<Competition> findCompetitionByMember(Integer num);
}
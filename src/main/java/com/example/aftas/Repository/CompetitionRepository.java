package com.example.aftas.Repository;

import com.example.aftas.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition,Long> {


    public Competition findCompetitionByDate(LocalDate date);
    Competition findCompetitionByCode(String code);
    Page<Competition> findCompetitionByCode(String code, Pageable page);
}

package com.example.aftas.Repository;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking,Long> {

    Integer countRankingsByCompetition(Competition competition);

    Ranking findRankingByCompetitionAndMember(Competition competition, Member member);
}

package com.example.aftas.Repository;

import com.example.aftas.embedded.RankingPrimaryKey;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingPrimaryKey> {

    Integer countRankingsByCompetition(Competition competition);

    Ranking findRankingByCompetitionAndMember(Competition competition, Member member);

    List<Ranking> findByCompetitionOrderByScoreDesc(Competition competition);
}

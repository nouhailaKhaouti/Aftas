package com.example.aftas.service.impl;

import com.example.aftas.Repository.RankingRepository;
import com.example.aftas.embedded.RankingPrimaryKey;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.exception.CustomException;
import com.example.aftas.exception.DateValidationException;
import com.example.aftas.exception.NotFoundException;
import com.example.aftas.service.facade.CompetitionService;
import com.example.aftas.service.facade.MemberService;
import com.example.aftas.service.facade.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    final private RankingRepository rankingRepository;
    final private CompetitionService competitionService;
    final private MemberService memberService;
    @Override
    public Ranking create(Ranking ranking) {
        Member member=memberService.findByNum(ranking.getMember().getNum());
        throwExceptionWhenMemberDoNotExist(member);
        Competition competition=competitionService.findCompetitionByCode(ranking.getCompetition());
        throwExceptionWhenCompetitionDoNotExist(competition);

        if(rankingRepository.findRankingByCompetitionAndMember(competition,member)!=null){
            throw new CustomException("this member is already registered to this competition ", HttpStatus.FOUND);
        }

        if(rankingRepository.countRankingsByCompetition(competition).equals(competition.getNumberOfParticipants())){
            throw new CustomException("you can't register this member in this competition , there's no more available places ", HttpStatus.CONFLICT);
        }

        if(LocalDate.now().isAfter(competition.getDate())){
            throw new DateValidationException();
        }

        ranking.setId(new RankingPrimaryKey(competition.getId(), member.getId()));
        ranking.setCompetition(competition);
        ranking.setMember(member);
        ranking.setScore(0);
        ranking.setRank(0);
        return rankingRepository.save(ranking);
    }

    @Override
    public Ranking update(Ranking ranking) {
        Ranking ranking1=rankingRepository.findRankingByCompetitionAndMember(ranking.getCompetition(),ranking.getMember());
        throwExceptionWhenRankingDoNotExist(ranking1);
        throwExceptionWhenMemberDoNotExist(ranking1.getMember());
        Competition competition=competitionService.findById(ranking1.getCompetition());
        ranking1.setRank(ranking.getRank());
        ranking1.setScore(ranking.getScore());
        throwExceptionWhenCompetitionDoNotExist(competition);
        return rankingRepository.save(ranking1);
    }


    @Override
    public List<Ranking> sortMemberWithPoints(String code){
        Competition competition=competitionService.findCompetitionByCode(Competition.builder().code(code).build());
        throwExceptionWhenCompetitionDoNotExist(competition);
        List<Ranking> rankings=rankingRepository.findByCompetitionOrderByScoreAsc(competition);
        for (int i = 1; i < rankings.size()-1; i++) {
            if((i!=1)&&(rankings.get(i).getScore().equals(rankings.get(i-1).getScore()))){
                rankings.get(i).setRank(rankings.get(i-1).getRank());
            }else{
                rankings.get(i).setRank(i);
            }
        }
        return rankings;
    }
    @Override
    public void delete(Ranking ranking) {
        rankingRepository.delete(ranking);
    }
    @Override
    public Ranking findById(Ranking ranking) {
        return rankingRepository.findRankingByCompetitionAndMember(ranking.getCompetition(),ranking.getMember());
    }

    @Override
    public List<Ranking> findAll() {
        return rankingRepository.findAll();
    }

    public Ranking findByMemberAndCompetition(Member member,Competition competition){
        return rankingRepository.findRankingByCompetitionAndMember(competition,member);
    }
    public void throwExceptionWhenMemberDoNotExist(Member member){
        if(member==null){
            throw new NotFoundException();
        }
    }

    public void throwExceptionWhenCompetitionDoNotExist(Competition competition){
        if(competition==null){
            throw new NotFoundException();
        }
    }

    public void throwExceptionWhenRankingDoNotExist(Ranking ranking){
        if(ranking==null){
            throw new NotFoundException();
        }
    }

}

package com.example.aftas.service.impl;

import com.example.aftas.Repository.FishRepository;
import com.example.aftas.Repository.HuntingRepository;
import com.example.aftas.entities.*;
import com.example.aftas.exception.CustomException;
import com.example.aftas.exception.DateValidationException;
import com.example.aftas.exception.NotFoundException;
import com.example.aftas.service.facade.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {
    final private HuntingRepository huntingRepository;
    final private FishService fishService;
    final private CompetitionService competitionService;
    final private MemberService memberService;
    final private RankingService rankingService;
    @Override
    public Hunting create(Hunting hunting,Double weight) {
        Member member=memberService.findByNum(hunting.getMember().getNum());
        throwExceptionWhenMemberDoNotExist(member);
        Competition competition=competitionService.findCompetitionByCode(hunting.getCompetition());
        throwExceptionWhenCompetitionDoNotExist(competition);
        Ranking ranking=rankingService.findByMemberAndCompetition(member,competition);
        if(ranking==null){
            throw new CustomException("this member is not registered to this competition",HttpStatus.UNAUTHORIZED);
        }

        if(!LocalDate.now().isEqual(competition.getDate())){
            throw new DateValidationException();
        }

        Fish fish=fishService.findByName(hunting.getFish());
        if(fish==null){
            throw new NotFoundException();
        }

        if(weight<fish.getAverageWeight()){
            throw  new CustomException("the fish can't be counted as a hunt , as it doesn't meet the min weight required", HttpStatus.CONFLICT);
        }

        ranking.setScore(ranking.getRank()+fish.getLevel().getPoints());
        rankingService.update(ranking);
        hunting.setMember(member);
        hunting.setCompetition(competition);
        Hunting hunting1=huntingRepository.findHuntingByFishAndMemberAndCompetition(fish,hunting.getMember(),hunting.getCompetition());
        if(hunting1!=null){
            return update(hunting1);
        }
        return huntingRepository.save(hunting);
    }

    @Override
    public Hunting update(Hunting hunting) {
        Optional<Hunting> hunting1=huntingRepository.findById(hunting.getId());
        if(hunting1.isPresent()){
            hunting1.get().setNumberOfFish(hunting1.get().getNumberOfFish()+1);
            return huntingRepository.save(hunting);
        }
        throw new NotFoundException();
    }

    @Override
    public void delete(Hunting hunting) {
        huntingRepository.delete(hunting);
    }

    @Override
    public Optional<Hunting> findById(Hunting hunting) {
        return huntingRepository.findById(hunting.getId());
    }

    @Override
    public List<Hunting> findAll() {
        return huntingRepository.findAll();
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
}

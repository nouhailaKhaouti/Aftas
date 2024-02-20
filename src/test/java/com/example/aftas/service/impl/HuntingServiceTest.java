package com.example.aftas.service.impl;
import com.example.aftas.Repository.HuntingRepository;
import com.example.aftas.entities.*;
import com.example.aftas.exception.CustomException;
import com.example.aftas.exception.NotFoundException;
import com.example.aftas.service.facade.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class HuntingServiceTest {
    @Mock
    private MemberService memberService;

    @Mock
    private CompetitionService competitionService;

    @Mock
    private RankingService rankingService;

    @Mock
    private FishService fishService;

    @Mock
    private HuntingRepository huntingRepository;

    @InjectMocks
    private HuntingServiceImpl huntingService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        Hunting hunting = new Hunting();
        hunting.setMember(Member.builder().num(3).build());
        hunting.setCompetition(Competition.builder().code("code").build());
        hunting.setFish(Fish.builder().name("name").level(Level.builder().id(1L).points(100).build()).build());

        Double weight = 10.0;
        Double averageWeight = 5.0;

        Member member = new Member();
        when(memberService.findByNum(any())).thenReturn(member);

        Competition competition = new Competition();
        competition.setDate(LocalDate.now());
        when(competitionService.findCompetitionByCode(any())).thenReturn(competition);

        Ranking ranking = new Ranking();
        when(rankingService.findByMemberAndCompetition(any(), any())).thenReturn(ranking);

        Fish fish = new Fish();
        fish.setAverageWeight(averageWeight);
        when(fishService.findByName(any())).thenReturn(fish);

        when(huntingRepository.findHuntingByFishAndMemberAndCompetition(any(), any(), any())).thenReturn(null);
        when(huntingRepository.save(any())).thenReturn(hunting);

        Hunting createdHunting = huntingService.create(hunting, weight);

/*        verify(memberService).findByNum(any());
        verify(competitionService).findCompetitionByCode(any());
        verify(rankingService).findByMemberAndCompetition(any(), any());
        verify(fishService).findByName(any());
        verify(huntingRepository).findHuntingByFishAndMemberAndCompetition(any(), any(), any());
        verify(huntingRepository).save(any());*/

        Assertions.assertEquals(hunting, createdHunting);
    }

    @Test
    public void testCreate_WhenMemberDoesNotExist() {
        Hunting hunting = new Hunting();
        hunting.setMember(Member.builder().num(3).build());
        hunting.setCompetition(Competition.builder().code("code").build());
        hunting.setFish(Fish.builder().name("name").build());

        Double weight = 10.0;

        when(memberService.findByNum(any())).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> huntingService.create(hunting, weight));

        verify(memberService).findByNum(any());
    }

    @Test
    public void testCreate_WhenCompetitionDoesNotExist() {
        Hunting hunting = new Hunting();
        hunting.setMember(Member.builder().num(3).build());
        hunting.setCompetition(Competition.builder().code("code").build());
        hunting.setFish(Fish.builder().name("name").build());

        Double weight = 10.0;

        Member member = new Member();
        when(memberService.findByNum(any())).thenReturn(member);
        when(competitionService.findCompetitionByCode(any())).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> huntingService.create(hunting, weight));

        verify(competitionService).findCompetitionByCode(any());
    }

    @Test
    public void testCreate_WhenRankingDoesNotExist() {
        Hunting hunting = new Hunting();
        hunting.setMember(Member.builder().num(3).build());
        hunting.setCompetition(Competition.builder().code("code").build());
        hunting.setFish(Fish.builder().name("name").build());

        Double weight = 10.0;

        Member member = new Member();
        Competition competition = new Competition();

        when(memberService.findByNum(any())).thenReturn(member);
        when(competitionService.findCompetitionByCode(any())).thenReturn(competition);
        when(rankingService.findByMemberAndCompetition(any(), any())).thenReturn(null);

        Assertions.assertThrows(CustomException.class, () -> huntingService.create(hunting, weight));

        verify(rankingService).findByMemberAndCompetition(any(), any());
    }

    @Test
    public void testCreate_WhenFishDoesNotExist() {
        Hunting hunting = new Hunting();
        hunting.setMember(Member.builder().num(3).build());
        hunting.setCompetition(Competition.builder().code("code").build());
        hunting.setFish(Fish.builder().name("name").build());

        Double weight = 10.0;

        Member member = new Member();
        Competition competition = new Competition();
        Ranking ranking = new Ranking();

        when(memberService.findByNum(any())).thenReturn(member);
        when(competitionService.findCompetitionByCode(any())).thenReturn(competition);
        when(rankingService.findByMemberAndCompetition(any(), any())).thenReturn(ranking);
        when(fishService.findByName(any())).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> huntingService.create(hunting, weight));

        verify(fishService).findByName(any());
    }

    @Test
    public void testCreate_WhenWeightIsBelowAverage() {
        Hunting hunting = new Hunting();
        hunting.setMember(Member.builder().num(3).build());
        hunting.setCompetition(Competition.builder().code("code").build());
        hunting.setFish(Fish.builder().name("name").build());

        Double weight = 3.0;
        Double averageWeight = 5.0;

        Member member = new Member();
        Competition competition = new Competition();
        Ranking ranking = new Ranking();
        Fish fish = new Fish();
        fish.setAverageWeight(averageWeight);

        when(memberService.findByNum(any())).thenReturn(member);
        when(competitionService.findCompetitionByCode(any())).thenReturn(competition);
        when(rankingService.findByMemberAndCompetition(any(), any())).thenReturn(ranking);
        when(fishService.findByName(any())).thenReturn(fish);

        Assertions.assertThrows(CustomException.class, () -> huntingService.create(hunting, weight));

        verify(fishService).findByName(any());
    }

    @Test
    public void testCreate_WhenHuntingAlreadyExists() {
        // Mocking necessary dependencies
        Hunting hunting = new Hunting();
        hunting.setMember(new Member());
        hunting.setMember(Member.builder().num(3).build());
        hunting.setCompetition(Competition.builder().code("code").build());
        hunting.setFish(Fish.builder().name("name").build());

        Double weight = 10.0;

        Member member = new Member();
        Competition competition = new Competition();
        Ranking ranking = new Ranking();
        Fish fish = new Fish();

        when(memberService.findByNum(any())).thenReturn(member);
        when(competitionService.findCompetitionByCode(any())).thenReturn(competition);
        when(rankingService.findByMemberAndCompetition(any(), any())).thenReturn(ranking);
        when(fishService.findByName(any())).thenReturn(fish);
        when(huntingRepository.findHuntingByFishAndMemberAndCompetition(any(), any(), any())).thenReturn(hunting);

        Assertions.assertThrows(NotFoundException.class, () -> huntingService.create(hunting, weight));

    }

    @Test
    public void testCreate_WhenHuntingDoesNotExist() {
        Hunting hunting = new Hunting();
        hunting.setMember(Member.builder().num(3).build());
        hunting.setCompetition(Competition.builder().code("code").build());
        hunting.setFish(Fish.builder().name("name").build());

        Double weight = 10.0;

        Member member = new Member();
        Competition competition = new Competition();
        Ranking ranking = new Ranking();
        Fish fish = new Fish();

        when(memberService.findByNum(any())).thenReturn(member);
        when(competitionService.findCompetitionByCode(any())).thenReturn(competition);
        when(rankingService.findByMemberAndCompetition(any(), any())).thenReturn(ranking);
        when(fishService.findByName(any())).thenReturn(fish);
        when(huntingRepository.findHuntingByFishAndMemberAndCompetition(any(), any(), any())).thenReturn(null);
        when(huntingRepository.save(any())).thenReturn(hunting);

        Hunting createdHunting = huntingService.create(hunting, weight);

        verify(huntingRepository).save(any());

        Assertions.assertEquals(hunting, createdHunting);
    }
}
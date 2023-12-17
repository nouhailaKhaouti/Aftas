package com.example.aftas.seeders;

import com.example.aftas.Repository.CompetitionRepository;
import com.example.aftas.entities.Competition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompetitionSeeder {

    private final CompetitionRepository competitionRepository;

    void seedCompetitionData() {
        if(competitionRepository.count()==0){

        List<Competition> competitionList = new ArrayList<>();

        competitionList.add(Competition.builder()
                .date(LocalDate.now().minusDays(1))
                .startTime(LocalTime.of(10, 0))
                .code("EVT001")
                .endTime(LocalTime.of(12, 0))
                .numberOfParticipants(50)
                .location("Venue A")
                .amount(1000.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now())
                .startTime(LocalTime.of(14, 0))
                .code("EVT002")
                .endTime(LocalTime.of(16, 0))
                .numberOfParticipants(30)
                .location("Venue B")
                .amount(1500.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(9, 30))
                .code("EVT003")
                .endTime(LocalTime.of(11, 30))
                .numberOfParticipants(20)
                .location("Venue C")
                .amount(800.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(2))
                .startTime(LocalTime.of(13, 0))
                .code("EVT004")
                .endTime(LocalTime.of(15, 0))
                .numberOfParticipants(40)
                .location("Venue D")
                .amount(1200.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(3))
                .startTime(LocalTime.of(10, 0))
                .code("EVT005")
                .endTime(LocalTime.of(12, 0))
                .numberOfParticipants(50)
                .location("Venue E")
                .amount(900.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(4))
                .startTime(LocalTime.of(14, 0))
                .code("EVT006")
                .endTime(LocalTime.of(16, 0))
                .numberOfParticipants(30)
                .location("Venue F")
                .amount(1600.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(5))
                .startTime(LocalTime.of(9, 30))
                .code("EVT007")
                .endTime(LocalTime.of(11, 30))
                .numberOfParticipants(20)
                .location("Venue G")
                .amount(700.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(6))
                .startTime(LocalTime.of(13, 0))
                .code("EVT008")
                .endTime(LocalTime.of(15, 0))
                .numberOfParticipants(40)
                .location("Venue H")
                .amount(1100.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(7))
                .startTime(LocalTime.of(10, 0))
                .code("EVT009")
                .endTime(LocalTime.of(12, 0))
                .numberOfParticipants(50)
                .location("Venue I")
                .amount(950.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(8))
                .startTime(LocalTime.of(14, 0))
                .code("EVT010")
                .endTime(LocalTime.of(16, 0))
                .numberOfParticipants(30)
                .location("Venue J")
                .amount(1400.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(9))
                .startTime(LocalTime.of(9, 30))
                .code("EVT011")
                .endTime(LocalTime.of(11, 30))
                .numberOfParticipants(20)
                .location("Venue K")
                .amount(600.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(10))
                .startTime(LocalTime.of(13, 0))
                .code("EVT012")
                .endTime(LocalTime.of(15, 0))
                .numberOfParticipants(40)
                .location("Venue L")
                .amount(1300.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(11))
                .startTime(LocalTime.of(10, 0))
                .code("EVT013")
                .endTime(LocalTime.of(12, 0))
                .numberOfParticipants(50)
                .location("Venue M")
                .amount(850.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(12))
                .startTime(LocalTime.of(14, 0))
                .code("EVT014")
                .endTime(LocalTime.of(16, 0))
                .numberOfParticipants(30)
                .location("Venue N")
                .amount(1200.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(13))
                .startTime(LocalTime.of(9, 30))
                .code("EVT015")
                .endTime(LocalTime.of(11, 30))
                .numberOfParticipants(20)
                .location("Venue O")
                .amount(700.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(14))
                .startTime(LocalTime.of(13, 0))
                .code("EVT016")
                .endTime(LocalTime.of(15, 0))
                .numberOfParticipants(40)
                .location("Venue P")
                .amount(1400.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(15))
                .startTime(LocalTime.of(10, 0))
                .code("EVT017")
                .endTime(LocalTime.of(12, 0))
                .numberOfParticipants(50)
                .location("Venue Q")
                .amount(900.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(16))
                .startTime(LocalTime.of(14, 0))
                .code("EVT018")
                .endTime(LocalTime.of(16, 0))
                .numberOfParticipants(30)
                .location("Venue R")
                .amount(1600.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(17))
                .startTime(LocalTime.of(9, 30))
                .code("EVT019")
                .endTime(LocalTime.of(11, 30))
                .numberOfParticipants(20)
                .location("Venue S")
                .amount(750.0)
                .build());

        competitionList.add(Competition.builder()
                .date(LocalDate.now().plusDays(18))
                .startTime(LocalTime.of(13, 0))
                .code("EVT020")
                .endTime(LocalTime.of(15, 0))
                .numberOfParticipants(40)
                .location("Venue T")
                .amount(1100.0)
                .build());
        competitionRepository.saveAll(competitionList);
    }
    }
}

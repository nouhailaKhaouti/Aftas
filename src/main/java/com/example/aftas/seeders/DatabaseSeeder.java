package com.example.aftas.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    final LevelSeeder levelSeeder;
    final FishSeeder fishSeeder;
    final MemberSeeder memberSeeder;
    final CompetitionSeeder competitionSeeder;
    @Override
    public void run(String... args)  {
         levelSeeder.seedLevelData();
         fishSeeder.seedFishData();
         memberSeeder.seedMemberData();
         competitionSeeder.seedCompetitionData();
    }

}

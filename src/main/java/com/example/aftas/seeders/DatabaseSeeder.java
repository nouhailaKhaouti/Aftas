package com.example.aftas.seeders;

import com.example.aftas.Repository.FishRepository;
import com.example.aftas.Repository.LevelRepository;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final FishRepository fishRepository;

    private final LevelRepository levelRepository;
    @Override
    public void run(String... args) throws Exception {
        if (levelRepository.count() == 0) {
            seedLevelData();
        }
        if (fishRepository.count() == 0 ) {
            seedFishData();
        }
    }
    private void seedLevelData() {
        List<Level> levelList = new ArrayList<>();

        levelList.add(Level.builder().description("Easy").points(100).code(1).build());
        levelList.add(Level.builder().description("Medium").points(200).code(2).build());
        levelList.add(Level.builder().description("Hard").points(300).code(3).build());
        levelList.add(Level.builder().description("Advanced").points(400).code(4).build());
        levelList.add(Level.builder().description("Expert").points(500).code(5).build());
        levelRepository.saveAll(levelList);
    }
    private void seedFishData() {
        List<Fish> fishList = new ArrayList<>();

        fishList.add(Fish.builder().name("Bass").averageWeight(3.0).level(Level.builder().id(2L).build()).build());
        fishList.add(Fish.builder().name("Tuna").averageWeight(1.0).level(Level.builder().id(1L).build()).build());
        fishList.add(Fish.builder().name("Cod").averageWeight(8.0).level(Level.builder().id(3L).build()).build());
        fishList.add(Fish.builder().name("Mackerel").averageWeight(5.0).level(Level.builder().id(2L).build()).build());
        fishList.add(Fish.builder().name("Pike").averageWeight(0.5).level(Level.builder().id(1L).build()).build());
        fishList.add(Fish.builder().name("Perch").averageWeight(10.0).level(Level.builder().id(3L).build()).build());
        fishList.add(Fish.builder().name("Catfish").averageWeight(2.0).level(Level.builder().id(2L).build()).build());
        fishList.add(Fish.builder().name("Salmon").averageWeight(10.0).level(Level.builder().id(3L).build()).build());
        fishList.add(Fish.builder().name("Trout").averageWeight(15.0).level(Level.builder().id(4L).build()).build());
        fishList.add(Fish.builder().name("Carp").averageWeight(2.0).level(Level.builder().id(2L).build()).build());
        fishList.add(Fish.builder().name("Swordfish").averageWeight(20.0).level(Level.builder().id(5L).build()).build());
        fishList.add(Fish.builder().name("Marlin").averageWeight(2.0).level(Level.builder().id(3L).build()).build());
        fishList.add(Fish.builder().name("Barracuda").averageWeight(3.0).level(Level.builder().id(2L).build()).build());
        fishList.add(Fish.builder().name("Mahi-Mahi").averageWeight(5.0).level(Level.builder().id(3L).build()).build());
        fishList.add(Fish.builder().name("Bluefish").averageWeight(15.0).level(Level.builder().id(2L).build()).build());
        fishList.add(Fish.builder().name("Walleye").averageWeight(10.0).level(Level.builder().id(2L).build()).build());
        fishList.add(Fish.builder().name("Snapper").averageWeight(200.0).level(Level.builder().id(2L).build()).build());
        fishList.add(Fish.builder().name("Grouper").averageWeight( 150.0).level(Level.builder().id(3L).build()).build());
        fishList.add(Fish.builder().name("Haddock").averageWeight( 5.0).level(Level.builder().id(2L).build()).build());
        fishList.add(Fish.builder().name("Shark").averageWeight(50.0).level(Level.builder().id(5L).build()).build());
        fishList.add(Fish.builder().name("Eel").averageWeight(2.0).level(Level.builder().id(2L).build()).build());

        // Save the fish data to the database
        fishRepository.saveAll(fishList);
    }
}

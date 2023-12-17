package com.example.aftas.seeders;

import com.example.aftas.Repository.LevelRepository;
import com.example.aftas.entities.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LevelSeeder {
    private  final LevelRepository levelRepository;

     void seedLevelData() {
        if (levelRepository.count() == 0) {

            List<Level> levelList = new ArrayList<>();

            levelList.add(Level.builder().description("Easy").points(100).code(1).build());
            levelList.add(Level.builder().description("Medium").points(200).code(2).build());
            levelList.add(Level.builder().description("Hard").points(300).code(3).build());
            levelList.add(Level.builder().description("Advanced").points(400).code(4).build());
            levelList.add(Level.builder().description("Expert").points(500).code(5).build());
            levelRepository.saveAll(levelList);
        }
    }
}

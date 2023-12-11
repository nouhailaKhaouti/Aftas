package com.example.aftas.service.impl;

import com.example.aftas.Repository.LevelRepository;
import com.example.aftas.entities.Level;
import com.example.aftas.service.facade.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    final private LevelRepository levelRepository;
    @Override
    public Level create(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level update(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public void delete(Level level) {
        levelRepository.delete(level);
    }

    @Override
    public Optional<Level> findById(Level level) {
        return levelRepository.findById(level.getId());
    }

    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }
}

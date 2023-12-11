package com.example.aftas.service.facade;

import com.example.aftas.entities.Level;

import java.util.List;
import java.util.Optional;

public interface LevelService {

    Level create(Level member);

    Level update(Level member);

    void delete(Level member);

    Optional<Level> findById(Level member);

    List<Level> findAll();

}

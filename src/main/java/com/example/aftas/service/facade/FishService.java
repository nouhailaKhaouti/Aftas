package com.example.aftas.service.facade;

import com.example.aftas.entities.Fish;

import java.util.List;

public interface FishService {

    Fish create(Fish member);

    Fish update(Fish member);

    void delete(Fish member);

    Fish findById(Fish member);

    List<Fish> findAll();

     Fish findByName(Fish fish);


    }

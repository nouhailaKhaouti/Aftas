package com.example.aftas.service.impl;

import com.example.aftas.Repository.FishRepository;
import com.example.aftas.entities.Fish;
import com.example.aftas.service.facade.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {
    final private FishRepository fishRepository;

    @Override
    public Fish create(Fish fish) {
        return fishRepository.save(fish);
    }

    @Override
    public Fish update(Fish fish) {
        return fishRepository.save(fish);
    }

    @Override
    public void delete(Fish fish) {
        fishRepository.delete(fish);
    }

    @Override
    public Fish findById(Fish fish) {
        Optional<Fish> fish1=fishRepository.findById(fish.getId());
        return fish1.orElse(null);
    }

    public Fish findByName(Fish fish){
        return fishRepository.findFishByName(fish.getName());
    }
    @Override
    public List<Fish> findAll() {
        return fishRepository.findAll();
    }
}

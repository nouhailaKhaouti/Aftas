package com.example.aftas.service.impl;

import com.example.aftas.Repository.HuntingRepository;
import com.example.aftas.entities.Hunting;
import com.example.aftas.service.facade.HuntingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {
    final private HuntingRepository huntingRepository;
    @Override
    public Hunting create(Hunting hunting) {
        return huntingRepository.save(hunting);
    }

    @Override
    public Hunting update(Hunting hunting) {
        return huntingRepository.save(hunting);
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
}

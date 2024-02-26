package com.example.aftas.controller;


import com.example.aftas.controller.vm.Fish.requestFish;
import com.example.aftas.controller.vm.Fish.responseFish;
import com.example.aftas.entities.Fish;
import com.example.aftas.service.facade.FishService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Aftas/api/fish")
public class FishController {

    final private FishService fishService;


    final ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<?> getAllFishes() {
            List<Fish> fishes = fishService.findAll();
            return new ResponseEntity<>(fishes.stream().map(f->modelMapper.map(f, responseFish.class)).toList(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")

    @PostMapping("/")
    public ResponseEntity<?> addFish(@RequestBody() Fish fish) {
            Fish addedFish = fishService.create(fish);
            return new ResponseEntity<>(addedFish, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFish(@PathVariable("id") long id, @RequestBody() Fish fish) {
            fish.setId(id);
            Fish addedFish = fishService.update(fish);
            return new ResponseEntity<>(addedFish, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFish(@PathVariable("id") long id) {

            Fish fish=new Fish();
            fish.setId(id);
            fishService.delete(fish);

            return new ResponseEntity<>(HttpStatus.OK);
    }

}

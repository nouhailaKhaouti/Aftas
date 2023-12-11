package com.example.aftas.controller;


import com.example.aftas.entities.Fish;
import com.example.aftas.service.facade.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Aftas/api/fish")
public class FishController {

    final private FishService fishService;

/*
    final ModelMapper modelMapper;
*/

    @GetMapping("/")
    public ResponseEntity<?> getAllFishs() {
            List<Fish> fishs = fishService.findAll();

            return new ResponseEntity<>(fishs, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addFish(@RequestBody() Fish fish) {
            Fish addedFish = fishService.create(fish);
            return new ResponseEntity<>(addedFish, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFish(@PathVariable("id") long id, @RequestBody() Fish fish) {
            fish.setId(id);
            Fish addedFish = fishService.update(fish);
            return new ResponseEntity<>(addedFish, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFish(@PathVariable("id") long id) {

            Fish fish=new Fish();
            fish.setId(id);
            fishService.delete(fish);

            return new ResponseEntity<>(HttpStatus.OK);
    }

}

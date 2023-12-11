package com.example.aftas.controller;


import com.example.aftas.entities.Hunting;
import com.example.aftas.service.facade.HuntingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Aftas/api/hunting")
public class HuntingController {

    final private HuntingService huntingService;

/*
    final ModelMapper modelMapper;
*/

    @GetMapping("/")
    public ResponseEntity<?> getAllHuntings() {
            List<Hunting> huntings = huntingService.findAll();

            return new ResponseEntity<>(huntings, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addHunting(@RequestBody() Hunting hunting) {
            Hunting addedHunting = huntingService.create(hunting);
            return new ResponseEntity<>(addedHunting, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHunting(@PathVariable("id") long id, @RequestBody() Hunting hunting) {
            hunting.setId(id);
            Hunting addedHunting = huntingService.update(hunting);
            return new ResponseEntity<>(addedHunting, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHunting(@PathVariable("id") long id) {

            Hunting hunting=new Hunting();
            hunting.setId(id);
            huntingService.delete(hunting);

            return new ResponseEntity<>(HttpStatus.OK);
    }

}

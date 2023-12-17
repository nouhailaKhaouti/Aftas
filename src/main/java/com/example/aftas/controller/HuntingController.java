package com.example.aftas.controller;


import com.example.aftas.controller.vm.hunting.request.RequestHuntingWithOutFish;
import com.example.aftas.controller.vm.hunting.request.requestHuntingWithWeight;
import com.example.aftas.controller.vm.hunting.response.responseHunting;
import com.example.aftas.entities.Hunting;
import com.example.aftas.service.facade.HuntingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Aftas/api/hunting")
public class HuntingController {

    final private HuntingService huntingService;


    final ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<?> getAllHuntings() {
            List<Hunting> huntings = huntingService.findAll();

            return new ResponseEntity<>(huntings, HttpStatus.OK);
    }

    @GetMapping("/member/competition")
    public ResponseEntity<?> getHuntingsByMemberAndCompetition(@Valid @RequestBody RequestHuntingWithOutFish request) {
        return new ResponseEntity<>(huntingService.findByMemberAndCompetition(request.ToHunting()).stream().map(h->modelMapper.map(h,responseHunting.class)).toList(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addHunting(@Valid  @RequestBody()requestHuntingWithWeight hunting) {
            Hunting addedHunting = huntingService.create(hunting.getHunting().ToHunting(), hunting.getWeight());
            return new ResponseEntity<>(modelMapper.map(addedHunting,responseHunting.class), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHunting(@PathVariable("id") long id) {

            Hunting hunting=new Hunting();
            hunting.setId(id);
            huntingService.delete(hunting);

            return new ResponseEntity<>(HttpStatus.OK);
    }

}

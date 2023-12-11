package com.example.aftas.controller;


import com.example.aftas.controller.vm.level.Request.addLevel;
import com.example.aftas.controller.vm.level.Response.ResponseLevel;
import com.example.aftas.entities.Level;
import com.example.aftas.service.facade.LevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Aftas/api/level")
public class LevelController {

    final private LevelService levelService;


    final ModelMapper modelMapper;


    @GetMapping("/")
    public ResponseEntity<?> getAllLevels() {
            List<Level> levels = levelService.findAll();

            return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addLevel(@Valid @RequestBody() addLevel addlevel) {
            Level level=modelMapper.map(addlevel,Level.class);
            Level addedLevel = levelService.create(level);
            ResponseLevel responselevel=modelMapper.map(addedLevel,ResponseLevel.class);
            return new ResponseEntity<>(responselevel, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLevel(@PathVariable("id") long id, @RequestBody() Level level) {
        level.setId(id);
        Level addedLevel = levelService.update(level);
            return new ResponseEntity<>(addedLevel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLevel(@PathVariable("id") long id) {

            Level level=new Level();
            level.setId(id);
            levelService.delete(level);

            return new ResponseEntity<>(HttpStatus.OK);
    }

}

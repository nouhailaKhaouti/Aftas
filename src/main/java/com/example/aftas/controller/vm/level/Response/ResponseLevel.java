package com.example.aftas.controller.vm.level.Response;

import com.example.aftas.entities.Fish;
import lombok.Data;

import java.util.List;

@Data
public class ResponseLevel {
    private String description;

    private Integer code;

    private Integer points;

    private List<Fish> fishList;
}

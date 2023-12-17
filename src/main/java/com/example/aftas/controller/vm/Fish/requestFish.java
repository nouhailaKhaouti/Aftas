package com.example.aftas.controller.vm.Fish;

import com.example.aftas.controller.vm.level.Request.requestLevel;
import lombok.Data;

@Data
public class requestFish {
    private String name;
    private Double averageWeight;
    private requestLevel level;
}

package com.example.aftas.controller.vm.Fish;

import com.example.aftas.controller.vm.level.Response.ResponseLevel;
import lombok.Data;

@Data
public class responseFish {

    private String name;
    private Double averageWeight;
    private ResponseLevel level;
}

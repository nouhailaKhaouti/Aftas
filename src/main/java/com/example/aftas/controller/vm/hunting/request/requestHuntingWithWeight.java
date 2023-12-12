package com.example.aftas.controller.vm.hunting.request;

import com.example.aftas.controller.vm.hunting.request.RequestHunting;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class requestHuntingWithWeight {

    private RequestHunting hunting;

    @NotNull(message = "the weight can't be blank")
    @Min(0)
    private Double weight;
}

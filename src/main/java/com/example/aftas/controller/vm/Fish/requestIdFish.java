package com.example.aftas.controller.vm.Fish;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class requestIdFish {
    @NotNull(message = "the id can't be null")
    private Long id;
}

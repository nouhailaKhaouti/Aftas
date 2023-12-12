package com.example.aftas.controller.vm.level.Request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Value;

@Data
public class addLevel {

    @NotNull(message = "the description can't be null")
    @NotBlank(message = "the description can't be blank")
    @Size(max = 100, message = "the description you entered exceeded the length allowed")
    private String description;

    @NotNull(message = "the code can't be null")
    @Positive(message = "the code can't be negative")
    @Min(6)
    private Integer code;

    @NotNull(message = "points can't be null")
    @Positive(message = "the points can't be negative")
    @Min(600)
    private Integer points;
}

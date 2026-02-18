package com.example.CRUDCarApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {

    private Long id;

    @NotNull(message = "model is required")
    @NotBlank(message = "model is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{2,}$", message = "must contain at least 2 characters with only letters, numbers, and spaces")
    private String model;

    @NotNull(message = "make is required")
    @NotBlank(message = "make is required")
    @Pattern(regexp = "^[a-zA-Z ]{3,}$", message = "must contain at least 3 characters with only letters and spaces")
    private String make;

    @NotNull(message = "type is required")
    @NotBlank(message = "type is required")
    @Pattern(regexp = "^[a-zA-Z ]{3,}", message = "must contain at least 3 characters with only letters and spaces")
    private String type;
}

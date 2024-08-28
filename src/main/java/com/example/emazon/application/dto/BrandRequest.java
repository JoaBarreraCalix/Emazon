// application.dto.BrandRequest
package com.example.emazon.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
public class BrandRequest {

    @Schema(description = "Unique identifier of the brand", example = "1")
    private Long id;

    @Schema(description = "Name of the brand", example = "Nike")
    @NotEmpty(message = "The brand name must not be empty")
    @Size(max = 50, message = "The brand name must be a maximum of 50 characters")
    private String name;

    @Schema(description = "Description of the brand", example = "Sportswear and accessories.")
    @NotEmpty(message = "The brand description must not be empty")
    @Size(max = 120, message = "The brand description must be a maximum of 120 characters")
    private String description;
}

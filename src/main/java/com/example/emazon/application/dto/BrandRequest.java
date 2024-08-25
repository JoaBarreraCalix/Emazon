// application.dto.BrandRequest
package com.example.emazon.application.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
public class BrandRequest {

    @Schema(description = "Unique identifier of the brand", example = "1")
    private Long id;

    @Schema(description = "Name of the brand", example = "Nike")
    private String name;

    @Schema(description = "Description of the brand", example = "Sportswear and accessories.")
    private String description;
}

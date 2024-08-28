//application.dto.CategoryRequest

package com.example.emazon.application.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    @Schema(description = "Unique identifier of the category", example = "1")
    private Long id;

    @Schema(description = "Name of the category", example = "Electronics")
    @NotEmpty(message = "The category name must not be empty")
    @Size(max = 50, message = "The category name must be a maximum of 50 characters")
    private String name;

    @Schema(description = "Description of the category", example = "All kinds of electronic gadgets and devices.")
    @NotEmpty(message = "The category description must not be empty")
    @Size(max = 90, message = "The category description must be a maximum of 90 characters")
    private String description;
}
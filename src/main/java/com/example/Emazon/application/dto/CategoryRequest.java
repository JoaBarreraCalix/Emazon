//application.dto.CategoryRequest

package com.example.emazon.application.dto;


import com.example.emazon.common.Constants;
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
    @NotEmpty(message = Constants.CATEGORY_NOT_NULL_NAME)
    @Size(max = Constants.CATEGORY_MAX_NAME_SIZE, message = Constants.CATEGORY_MAX_NAME_MESSAGE)
    private String name;

    @Schema(description = "Description of the category", example = "All kinds of electronic gadgets and devices.")
    @NotEmpty(message = Constants.CATEGORY_NOT_NULL_DESCRIPTION)
    @Size(max = Constants.CATEGORY_MAX_DESCRIPTION_SIZE, message = Constants.CATEGORY_MAX_DESCRIPTION_MESSAGE)
    private String description;
}
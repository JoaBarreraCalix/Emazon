// application.dto.BrandRequest
package com.example.emazon.application.dto;

import com.example.emazon.common.Constants;
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
    @NotEmpty(message = Constants.BRAND_NOT_NULL_NAME)
    @Size(max = Constants.BRAND_MAX_NAME_SIZE, message = Constants.BRAND_MAX_NAME_MESSAGE)
    private String name;

    @Schema(description = "Description of the brand", example = "Sportswear and accessories.")
    @NotEmpty(message = Constants.BRAND_NOT_NULL_DESCRIPTION)
    @Size(max = Constants.BRAND_MAX_DESCRIPTION_SIZE, message = Constants.BRAND_MAX_DESCRIPTION_MESSAGE)
    private String description;
}

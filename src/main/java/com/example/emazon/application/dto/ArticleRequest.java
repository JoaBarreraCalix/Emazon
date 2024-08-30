// application.dto.ArticleRequest
package com.example.emazon.application.dto;

import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
public class ArticleRequest {

    @Schema(description = "Unique identifier of the article", example = "1")
    private Long id;

    @Schema(description = "Name of the article", example = "Air Jordan shoes")
    @NotEmpty(message = "The name must not be empty")
    @Size(max = 50, message = "The name must be a maximum of 50 characters")
    private String name;

    @Schema(description = "Description of the article", example = "Shoes made by nike in collaboration with Jordan")
    @NotEmpty(message = "The description must not be empty")
    @Size(max = 120, message = "The description must be a maximum of 120 characters")
    private String description;

    @Schema(description = "Number of available units of this article", example = "20")
    @Positive(message = "The quantity must be greater than 0")
    private int quantity;

    @Schema(description = "Value of the article per unit", example = "49.99")
    @Positive(message = "The price must be greater than 0")
    private double price;

    @Schema(description = "List of categories that cannot be null or exceed 3", example = "id: 2, name: Guayos,  description: Zapatos deportivos para futbol")
    @NotNull(message = "The article must have at least one category")
    @Size(min = 1, max = 3, message = "The article must have between 1 and 3 categories")
    @UniqueElements
    private List<Category> categories;

    @Schema(description = "Brand of the article", example = "Nike")
    @NotNull(message = "The article must have a brand")
    private Brand brand;

}

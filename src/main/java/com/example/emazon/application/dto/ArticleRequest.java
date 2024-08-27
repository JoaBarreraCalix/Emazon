// application.dto.ArticleRequest
package com.example.emazon.application.dto;

import com.example.emazon.domain.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleRequest {

    @Schema(description = "Unique identifier of the article", example = "1")
    private Long id;
    @Schema(description = "Name of the article", example = "Air Jordan shoes")
    private String name;
    @Schema(description = "Description of the article", example = "Shoes made by nike in collaboration with Jordan")
    private String description;
    @Schema(description = "Number of available units of this article", example = "20")
    private int quantity;
    @Schema(description = "Value of the article per unit", example = "49.99")
    private double price;
    @Schema(description = "List of categories that cannot be null or exceed 3", example = " {\n" +
            "            \"id\": 2,\n" +
            "            \"name\": \"Guayos\",\n" +
            "            \"description\": \"Zapatos deportivos para futbol\"\n" +
            "        }")
    private List<Category> categories;
}

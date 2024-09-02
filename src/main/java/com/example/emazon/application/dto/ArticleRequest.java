// application.dto.ArticleRequest
package com.example.emazon.application.dto;

import com.example.emazon.common.Constants;
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
    @NotEmpty(message = Constants.ARTICLE_NOT_NULL_NAME)
    @Size(max = Constants.ARTICLE_MAX_NAME_SIZE, message = Constants.ARTICLE_MAX_NAME_MESSAGE)
    private String name;

    @Schema(description = "Description of the article", example = "Shoes made by nike in collaboration with Jordan")
    @NotEmpty(message = Constants.ARTICLE_NOT_NULL_DESCRIPTION)
    @Size(max = Constants.ARTICLE_MAX_DESCRIPTION_SIZE, message = Constants.ARTICLE_MAX_DESCRIPTION_MESSAGE)
    private String description;

    @Schema(description = "Number of available units of this article", example = "20")
    @Positive(message = Constants.ARTICLE_NOT_NEGATIVE_NUMBERS)
    private int quantity;

    @Schema(description = "Value of the article per unit", example = "49.99")
    @Positive(message = Constants.ARTICLE_NOT_ZERO_VALUE)
    private double price;

    @Schema(description = "List of categories that cannot be null or exceed 3", example = "id: 2, name: Guayos,  description: Zapatos deportivos para futbol")
    @NotNull(message = Constants.ARTICLE_NOT_NULL_CATEGORY)
    @Size(min = Constants.ARTICLE_MIN_NUM_CATEGORIES, max = Constants.ARTICLE_MAX_NUM_CATEGORIES, message = Constants.ARTICLE_MAX_MIN_MESSAGE_CATEGORIES)
    @UniqueElements(message = Constants.ARTICLE_NOT_UNIQUE_CATEGORIES)
    private List<Category> categories;

    @Schema(description = "Brand of the article", example = "Nike")
    @NotNull(message = Constants.ARTICLE_NOT_NULL_BRAND)
    private Brand brand;

}

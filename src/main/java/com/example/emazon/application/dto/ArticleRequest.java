// application.dto.ArticleRequest
package com.example.emazon.application.dto;

import com.example.emazon.domain.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleRequest {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private List<Category> categories;
}

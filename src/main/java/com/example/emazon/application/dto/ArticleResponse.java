package com.example.emazon.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleResponse {

    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String brandName;
    private List<CategoryResponse> categories;
}

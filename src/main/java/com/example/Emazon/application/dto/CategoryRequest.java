//application.dto.CategoryRequest

package com.example.emazon.application.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private Long id;
    private String name;
    private String description;
}

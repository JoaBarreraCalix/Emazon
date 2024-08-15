//application.dto.CategoryRequest

package com.example.Emazon.application.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private Long id;
    private String name;
    private String description;
}

// application.mapper.CategoryRequestMapper
package com.example.Emazon.application.mapper;

import com.example.Emazon.application.dto.CategoryRequest;
import com.example.Emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryRequestMapper {

    Category toCategory(CategoryRequest categoryRequest);

    CategoryRequest toCategoryRequest(Category category);
}

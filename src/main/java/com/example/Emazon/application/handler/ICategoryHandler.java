// application.handler.ICategoryHandler
package com.example.Emazon.application.handler;

import com.example.Emazon.application.dto.CategoryRequest;

import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryRequest categoryRequest);

    List<CategoryRequest> getAllCategories();

    CategoryRequest getCategory(Long id);

    void updateCategory(CategoryRequest categoryRequest);

    void deleteCategory(Long id);
}

//application.handler.ICategoryHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.CategoryRequest;
import com.example.emazon.domain.utils.PageCustom;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryRequest categoryRequest);
    List<CategoryRequest> getAllCategories();
    CategoryRequest getCategory(Long id);
    void updateCategory(CategoryRequest categoryRequest);
    void deleteCategory(Long id);
    PageCustom<CategoryRequest> listCategories(int page, int size, String sortOrder);
}

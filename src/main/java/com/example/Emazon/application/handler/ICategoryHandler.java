// application.handler.ICategoryHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryRequest categoryRequest);

    List<CategoryRequest> getAllCategories();

    CategoryRequest getCategory(Long id);

    void updateCategory(CategoryRequest categoryRequest);

    void deleteCategory(Long id);

    Page<CategoryRequest> listCategories(Pageable pageable, String sortOrder);
}

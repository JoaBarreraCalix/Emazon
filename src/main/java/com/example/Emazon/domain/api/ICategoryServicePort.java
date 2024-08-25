//domain.api.ICategoryServicePort
package com.example.emazon.domain.api;

import com.example.emazon.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(Category category);

    List<Category> findAllCategories();

    Category getCategory(Long id);

    void updateCategory(Category category);

    void deleteCategory(Long id);
}

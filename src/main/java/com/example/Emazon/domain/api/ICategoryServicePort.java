//domain.api.ICategoryServicePort
package com.example.Emazon.domain.api;

import com.example.Emazon.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(Category category);

    List<Category> findAllCategories();

    Category getCategory(Long id);

    void updateCategory(Category category);

    void deleteCategory(Long id);
}

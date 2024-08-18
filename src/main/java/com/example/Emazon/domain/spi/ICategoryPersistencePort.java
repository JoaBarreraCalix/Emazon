//domain.spi.ICategoryPersistencePort
package com.example.Emazon.domain.spi;

import com.example.Emazon.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {


    void saveCategory(Category category);
    List<Category> findAllCategories();

    Category getCategory(Long id);

    void updateCategory(Category category);

    void deleteCategory(Long id);
}

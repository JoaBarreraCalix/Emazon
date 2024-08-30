//domain.spi.ICategoryPersistencePort
package com.example.emazon.domain.spi;

import com.example.emazon.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {


    void saveCategory(Category category);
    List<Category> findAllCategories();

    Optional<Category> getCategory(Long id);

    Optional<Category> findByName(String name);

    void updateCategory(Category category);

    void deleteCategory(Long id);
}

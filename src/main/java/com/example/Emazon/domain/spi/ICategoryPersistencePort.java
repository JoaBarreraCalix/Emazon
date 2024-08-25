//domain.spi.ICategoryPersistencePort
package com.example.emazon.domain.spi;

import com.example.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    List<Category> findAllCategories();

    Category getCategory(Long id);

    void updateCategory(Category category);

    void deleteCategory(Long id);

    Page<Category> listCategories(Pageable pageable, String sortOrder);
}
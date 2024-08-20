//domain.api.ICategoryServicePort
package com.example.emazon.domain.api;

import com.example.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(Category category);

    List<Category> findAllCategories();

    Category getCategory(Long id);

    void updateCategory(Category category);

    void deleteCategory(Long id);

    Page<Category> listCategories(Pageable pageable, String sortOrder);
}

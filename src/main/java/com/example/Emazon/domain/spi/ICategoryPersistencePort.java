//domain.spi.ICategoryPersistencePort
package com.example.emazon.domain.spi;

import com.example.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    List<Category> findAllCategories();

    Optional<Category> getCategory(Long id);  // Cambiado a Optional

    Optional<Category> findByName(String name);  // Nuevo método para la validación de duplicados

    void updateCategory(Category category);

    void deleteCategory(Long id);

    Page<Category> listCategories(Pageable pageable, String sortOrder);
}

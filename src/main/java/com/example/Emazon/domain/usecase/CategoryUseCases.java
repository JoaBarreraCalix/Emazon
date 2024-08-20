//domain.usecase.CategoryUseCases
package com.example.emazon.domain.usecase;

import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CategoryUseCases implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCases(ICategoryPersistencePort categoryPersistencePort) {

        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public void saveCategory(Category category) {
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryPersistencePort.findAllCategories();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryPersistencePort.getCategory(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryPersistencePort.updateCategory(category);

    }

    @Override
    public void deleteCategory(Long id) {
        categoryPersistencePort.deleteCategory(id);
    }

    @Override
    public Page<Category> listCategories(Pageable pageable, String sortOrder) {
        return categoryPersistencePort.listCategories(pageable, sortOrder);
    }
}

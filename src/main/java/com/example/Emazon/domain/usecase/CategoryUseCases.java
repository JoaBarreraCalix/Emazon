//domain.usecase.CategoryUseCases
package com.example.emazon.domain.usecase;

import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.exceptions.*;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import com.example.emazon.domain.utils.PageCustom;

import java.util.List;
import java.util.Optional;

public class CategoryUseCases implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCases(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        validateCategory(category);

        // Validación de categoría duplicada
        Optional<Category> existingCategory = categoryPersistencePort.findByName(category.getName());
        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistsException();
        }

        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryPersistencePort.findAllCategories();
    }

    @Override
    public Category getCategory(Long id) {
        // Validación de existencia de categoría
        return categoryPersistencePort.getCategory(id)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public void updateCategory(Category category) {
        validateCategory(category);
        categoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryPersistencePort.getCategory(id).isPresent()) {
            throw new CategoryNotFoundException();
        }
        categoryPersistencePort.deleteCategory(id);
    }

    @Override
    public PageCustom<Category> listCategories(int page, int size, String sortOrder) {
        // Implementar lógica de ordenamiento y paginación en la capa de dominio
        List<Category> sortedCategories = categoryPersistencePort.findAllCategories().stream()
                .sorted((c1, c2) -> "asc".equalsIgnoreCase(sortOrder)
                        ? c1.getName().compareTo(c2.getName())
                        : c2.getName().compareTo(c1.getName()))
                .toList();

        int start = page * size;
        int end = Math.min(start + size, sortedCategories.size());
        List<Category> paginatedCategories = sortedCategories.subList(start, end);

        return new PageCustom<>(paginatedCategories, page, size, sortedCategories.size());
    }

    private void validateCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new InvalidCategoryNameException();
        }
        if (category.getDescription() == null || category.getDescription().isEmpty()) {
            throw new InvalidCategoryDescriptionException();
        }
        if (category.getName().length() > 50) {
            throw new CategoryNameTooLongException();
        }
        if (category.getDescription().length() > 90) {
            throw new CategoryDescriptionTooLongException();
        }
    }
}

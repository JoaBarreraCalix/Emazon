//domain.usecase.CategoryUseCases
package com.example.emazon.domain.usecase;

import com.example.emazon.common.Constants;
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
        if (categoryPersistencePort.getCategory(id).isEmpty()) {
            throw new CategoryNotFoundException();
        }
        categoryPersistencePort.deleteCategory(id);
    }

    @Override
    public PageCustom<Category> listCategories(int page, int size, String sortOrder) {
        List<Category> sortedCategories = categoryPersistencePort.findAllCategories().stream()
                .sorted((c1, c2) -> Constants.ASC_VALUE.equalsIgnoreCase(sortOrder)
                        ? c1.getName().compareTo(c2.getName())
                        : c2.getName().compareTo(c1.getName()))
                .toList();

        int totalElements = sortedCategories.size();
        List<Category> paginatedCategories = sortedCategories.stream()
                .skip((long) page * size)
                .limit(size)
                .toList();

        return new PageCustom<>(paginatedCategories, page, size, totalElements);
    }

    private void validateCategory(Category category) {


        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new InvalidCategoryNameException();
        }
        if (category.getName().length() > Constants.CATEGORY_MAX_NAME_SIZE) {
            throw new CategoryNameTooLongException();
        }

        if (category.getDescription() == null || category.getDescription().trim().isEmpty()) {
            throw new InvalidCategoryDescriptionException();
        }
        if (category.getDescription().length() > Constants.CATEGORY_MAX_DESCRIPTION_SIZE) {
            throw new CategoryDescriptionTooLongException();
        }
    }
}

// infraestructure.out.jpa.adapter.CategoryJpaAdapter
package com.example.emazon.infrastructure.out.jpa.adapter;

import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import com.example.emazon.infrastructure.exception.NoDataFoundException;
import com.example.emazon.infrastructure.exception.CategoryAlreadyExistsException;
import com.example.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.example.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.example.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public List<Category> findAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        if (categoryEntities.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toCategoryList(categoryEntities);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryEntityMapper.toCategory(categoryRepository.findById(id)
                .orElseThrow(NoDataFoundException::new));
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

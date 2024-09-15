// infrastructure.out.jpa.adapter.CategoryJpaAdapter
package com.example.emazon.infrastructure.out.jpa.adapter;

import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import com.example.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.example.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.example.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public List<Category> findAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return categoryEntityMapper.toCategoryList(categoryEntities);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id).map(categoryEntityMapper::toCategory);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name).map(categoryEntityMapper::toCategory);
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

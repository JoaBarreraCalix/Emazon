// infraestructure.out.jpa.adapter.CategoryJpaAdapter
package com.example.emazon.infrastructure.out.jpa.adapter;

import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.spi.ICategoryPersistencePort;
import com.example.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.example.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.example.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Override
    public Page<Category> listCategories(Pageable pageable, String sortOrder) {
        Sort sort = Sort.by("name");
        sort = "desc".equalsIgnoreCase(sortOrder) ? sort.descending() : sort.ascending();
        return categoryRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort))
                .map(categoryEntityMapper::toCategory);
    }
}

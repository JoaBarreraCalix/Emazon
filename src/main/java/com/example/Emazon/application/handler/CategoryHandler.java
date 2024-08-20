// application.handler.CategoryHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.CategoryRequest;
import com.example.emazon.application.mapper.CategoryRequestMapper;
import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public List<CategoryRequest> getAllCategories() {
        return categoryServicePort.findAllCategories()
                .stream()
                .map(categoryRequestMapper::toCategoryRequest)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryRequest getCategory(Long id) {
        Category category = categoryServicePort.getCategory(id);
        return categoryRequestMapper.toCategoryRequest(category);
    }

    @Override
    public void updateCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryServicePort.deleteCategory(id);
    }

    @Override
    public Page<CategoryRequest> listCategories(Pageable pageable, String sortOrder) {
        return categoryServicePort.listCategories(pageable, sortOrder)
                .map(categoryRequestMapper::toCategoryRequest);
    }
}

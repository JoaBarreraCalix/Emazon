// application.handler.CategoryHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.CategoryRequest;
import com.example.emazon.application.mapper.CategoryRequestMapper;
import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.emazon.domain.utils.PageCustom;

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
    public PageCustom<CategoryRequest> listCategories(int page, int size, String sortOrder) {
        PageCustom<Category> categoryPage = categoryServicePort.listCategories(page, size, sortOrder);
        List<CategoryRequest> categoryRequests = categoryPage.getContent().stream()
                .map(categoryRequestMapper::toCategoryRequest)
                .collect(Collectors.toList());
        return new PageCustom<>(categoryRequests, categoryPage.getPageNumber(), categoryPage.getPageSize(), categoryPage.getTotalElements());
    }
}

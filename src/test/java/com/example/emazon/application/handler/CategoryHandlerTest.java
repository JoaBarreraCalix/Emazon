// src/test/java/com/example/Emazon/application/handler/CategoryHandlerTest.java
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.CategoryRequest;
import com.example.emazon.application.mapper.CategoryRequestMapper;
import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryHandlerTest {

    private ICategoryServicePort categoryServicePort;
    private CategoryRequestMapper categoryRequestMapper;
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        categoryServicePort = Mockito.mock(ICategoryServicePort.class);
        categoryRequestMapper = Mockito.mock(CategoryRequestMapper.class);
        categoryHandler = new CategoryHandler(categoryServicePort, categoryRequestMapper);
    }

    @Test
    void testSaveCategory() {
        CategoryRequest request = new CategoryRequest();
        request.setName("Electronics");
        request.setDescription("All kinds of electronic gadgets and devices.");

        Category category = new Category(1L, "Electronics", "All kinds of electronic gadgets and devices.");

        when(categoryRequestMapper.toCategory(request)).thenReturn(category);

        categoryHandler.saveCategory(request);

        verify(categoryServicePort, times(1)).saveCategory(any(Category.class));
    }

    @Test
    void testGetAllCategories() {
        Category category = new Category(1L, "Electronics", "All kinds of electronic gadgets and devices.");
        when(categoryServicePort.findAllCategories()).thenReturn(Collections.singletonList(category));
        when(categoryRequestMapper.toCategoryRequest(any(Category.class))).thenReturn(new CategoryRequest());

        List<CategoryRequest> result = categoryHandler.getAllCategories();

        assertEquals(1, result.size());
        verify(categoryServicePort, times(1)).findAllCategories();
    }
}

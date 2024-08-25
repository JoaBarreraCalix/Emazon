package com.example.emazon.application.handler;

import com.example.emazon.application.dto.CategoryRequest;
import com.example.emazon.application.mapper.CategoryRequestMapper;
import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.utils.PageCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryHandlerTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("All kinds of electronic gadgets and devices.");

        Category category = new Category();
        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);

        categoryHandler.saveCategory(categoryRequest);

        verify(categoryServicePort).saveCategory(category);
    }

    @Test
    void testGetAllCategories() {
        Category category = new Category();
        when(categoryServicePort.findAllCategories()).thenReturn(Collections.singletonList(category));

        CategoryRequest categoryRequest = new CategoryRequest();
        when(categoryRequestMapper.toCategoryRequest(category)).thenReturn(categoryRequest);

        List<CategoryRequest> categoryRequests = categoryHandler.getAllCategories();

        assertEquals(1, categoryRequests.size());
        assertEquals(categoryRequest, categoryRequests.get(0));
    }

    @Test
    void testGetCategory() {
        Category category = new Category();
        when(categoryServicePort.getCategory(1L)).thenReturn(category);

        CategoryRequest categoryRequest = new CategoryRequest();
        when(categoryRequestMapper.toCategoryRequest(category)).thenReturn(categoryRequest);

        CategoryRequest result = categoryHandler.getCategory(1L);

        assertEquals(categoryRequest, result);
    }

    @Test
    void testUpdateCategory() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setId(1L);
        categoryRequest.setName("Updated Electronics");
        categoryRequest.setDescription("Updated description.");

        Category category = new Category();
        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);

        categoryHandler.updateCategory(categoryRequest);

        verify(categoryServicePort).updateCategory(category);
    }

    @Test
    void testDeleteCategory() {
        categoryHandler.deleteCategory(1L);

        verify(categoryServicePort).deleteCategory(1L);
    }

    @Test
    void testListCategories_Success() {
        Category category = new Category(1L, "Electronics", "Devices");
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("Devices");

        when(categoryServicePort.listCategories(0, 10, "asc"))
                .thenReturn(new PageCustom<>(Collections.singletonList(category), 0, 10, 1));
        when(categoryRequestMapper.toCategoryRequest(category)).thenReturn(categoryRequest);

        PageCustom<CategoryRequest> result = categoryHandler.listCategories(0, 10, "asc");

        assertEquals(1, result.getContent().size());
        assertEquals("Electronics", result.getContent().get(0).getName());
    }
}
